package AST;

import Type.*;

public class TypeCheckVisitor {
    Environment<String, Function, Program> funcEnv;
    Environment<String, TypeNode, Function> varEnv;

    public TypeCheckVisitor(Program p) {
        this.funcEnv = new Environment<String, Function, Program>(p);
        // init when a function is visited
        this.varEnv = null;
    }

    public void visit(Program p) throws SemanticException {
        boolean containsMainFunction = false;
        // construct function environment before evaluating function bodies
        for (Function f : p.functionList) {
            // check if 'main' function exists and that it is of type 'void'
            if (isVoidMain(f.funcDecl)) {
                containsMainFunction = true;
            }

            String funcEnvKey = getEnvironmentKey(f.funcDecl);
            // add to environment, throwing error if function id already in use
            boolean noCollision = true;
            try {
                noCollision = this.funcEnv.add(funcEnvKey, f);
            } catch (Exception e) {
                System.out.println("Problem adding parameter and variable declarations to env:\n"+e);
                System.exit(-1);
            }
            if (!noCollision) {
                throw new SemanticException(
                    "Found duplicate function declaration",
                    f.funcDecl.id.getLineNumber(),
                    f.funcDecl.id.getLinePos()
                );
            }
        }

        if (!containsMainFunction) {
            throw new SemanticException(
                "Could not find function with name 'main' and type 'void'.",
                1, 0
            );
        }

        // now check each function body
        for (Function f : p.functionList) {
            f.accept(this);
        }
    }

    public void visit(Function f) throws SemanticException {
        // init with a pointer to its enclosing function
        this.varEnv = new Environment<String, TypeNode, Function>(f);

        // add all param/var declarations to new env
        try {
            addVarsToEnv(f.funcDecl.params, f.funcBody.varDecls);
        } catch (Exception e) {
            System.out.println("Problem adding parameter and variable declarations to env:\n"+e);
            System.exit(-1);
        }

        f.funcBody.accept(this);
    }

    // TODO: collapse duplicate loops by treating params & varDecls as similar objects
    private void addVarsToEnv(FormalParameterList params, Iterable<VariableDeclaration> varDecls) throws SemanticException, Exception {
        boolean noCollision;
        for (FormalParameter param : params) {
            if (param.type.toString().equals("void")) {
                throw new SemanticException(
                    "Found formal function param of type void",
                    param.id.getLineNumber(),
                    param.id.getLinePos()
                );
            }
            noCollision = this.varEnv.add(getEnvironmentKey(param), param.type);
            if (!noCollision) {
                throw new SemanticException(
                    "Found duplicate param declaration",
                    param.id.getLineNumber(),
                    param.id.getLinePos()
                );
            }
        }
        for (VariableDeclaration varDecl : varDecls) {
            if (varDecl.type.toString().equals("void")) {
                throw new SemanticException(
                    "Found variable declaration of type void",
                    varDecl.id.getLineNumber(),
                    varDecl.id.getLinePos()
                );
            }
            noCollision = this.varEnv.add(getEnvironmentKey(varDecl), varDecl.type);
            if (!noCollision) {
                throw new SemanticException(
                    "Found duplicate param declaration",
                    varDecl.id.getLineNumber(),
                    varDecl.id.getLinePos()
                );
            }
        }
    }

    public void visit(FunctionBody body) throws SemanticException {
        for (Statement s : body.statements) {
            s.accept(this);
        }
    }

    public void visit(Statement s) { }

    public void visit(ArrayAssignmentStatement s) throws SemanticException {
        // type check left side of assignment first
        Type elemType = this.typeCheckArrayExpression(s.id, s.index, s.id.getLineNumber(), s.id.getLinePos());
        // check that the type of the RHS matchces the element type of the target array
        Type assignedType = s.expr.accept(this);
        if (!elemType.equals(assignedType)) {
            throw new SemanticException(
                "Cannot assign expression of type '"+assignedType+"' to array of element type '"+elemType+"'",
                s.expr.lineNum,
                s.expr.pos
            );
        }
    }

    public void visit(PrintStatement s) throws SemanticException {
        Type t = s.expr.accept(this);
        if (t.toString().equals("void")) {
            throw new SemanticException(
                "Found expr in print statement of type 'void'",
                s.expr.lineNum,
                s.expr.pos
            );
        }
    }

    public void visit(PrintlnStatement s) throws SemanticException {
        Type t = s.expr.accept(this);
        if (t.toString().equals("void")) {
            throw new SemanticException(
                "Found expr in println statement of type 'void'",
                s.expr.lineNum,
                s.expr.pos
            );
        }
    }


    public void visit(WhileStatement s) throws SemanticException {
        Type cond = s.cond.accept(this);
        if (!cond.toString().equals("boolean")) {
            throw new SemanticException(
                "Found 'while' condition of type '"+cond+"', expected 'boolean'",
                s.cond.lineNum,
                s.cond.pos
            );
        }
    }

    public void visit(IfElseStatement s) throws SemanticException {
        Type cond = s.cond.accept(this);
        if (!cond.toString().equals("boolean")) {
            throw new SemanticException(
                "Found 'if' condition of type '"+cond+"', expected 'boolean'",
                s.cond.lineNum,
                s.cond.pos
            );
        }
    }


    public void visit(ScalarAssignmentStatement s) throws SemanticException {
        // ensure id exists in scope
        TypeNode expectedTypeNode = this.varEnv.lookup(s.id.name);
        if (expectedTypeNode == null) {
            throw new SemanticException(
                "Could not recognize identifier '"+s.id+"'",
                s.id.getLineNumber(),
                s.id.getLinePos()
            );
        }

        // ensure type of expression matches type of id
        Type expected = expectedTypeNode.type;
        Type actual = s.expr.accept(this);
        // TODO use an external class to determine what types can be assigned to what types
        if (!expected.equals(actual)) {
            throw new SemanticException(
                "Cannot assign expression of type '"+actual+"' to identifier of type '"+expected+"'",
                s.id.getLineNumber(),
                s.id.getLinePos()
            );
        }
    }

    public void visit(ReturnStatement s) throws SemanticException {
        Type expectedType = this.getType(this.varEnv.enclosingScope);
        Type actualType = (s.expr == null? new VoidType() : s.expr.accept(this));
        if (!expectedType.equals(actualType)) {
            throw new SemanticException(
                "Type of expression in return statement '"+actualType+"' does not match declared function type '"+expectedType+"'",
                s.expr.lineNum,
                s.expr.pos
            );
        }
    }

    public void visit(ExpressionStatement s) throws SemanticException {
        Type type = s.expr.accept(this);
    }

    // will be removed once method in class is made abstract
    public Type visit(Expression e) {
        return null;
    }

    public Type visit(FunctionCallExpression e) throws SemanticException {
        // ensure it's a function
        Function func = this.funcEnv.lookup(e.id.name);
        if (func == null) {
            throw new SemanticException(
                "Could not recognize function identifier '"+e.id+"'",
                e.lineNum,
                e.pos
            );
        }

        // ensure params length equal
        FormalParameterList expectedParams = func.funcDecl.params;
        ExpressionList actualParams = e.args;
        int expectedSize = expectedParams.size();
        int actualSize = actualParams.size();
        if (expectedSize != actualSize) {
            throw new SemanticException(
                "Number of given arguments "+actualSize+" != "+expectedSize,
                e.lineNum,
                e.pos
            );
        }

        // ensure types of ordered params
        Type expectedType, actualType;
        for (int i = 0; i < expectedSize; i++) {
            expectedType = expectedParams.get(i).type.type;
            actualType = actualParams.get(i).accept(this);
            if (!expectedType.equals(actualType)) {
                throw new SemanticException(
                    "Type of argument at index "+i+" should be "+expectedType+" but is "+actualType,
                    actualParams.get(i).lineNum,
                    actualParams.get(i).pos
                );
            }
        }
        return func.funcDecl.returnType.type;
    }

    public Type visit(IdentifierExpression e) throws SemanticException {
        TypeNode t = this.varEnv.lookup(e.id.name);
        if (t == null) {
            throw new SemanticException(
                "Could not recognize identifier '"+e.id+"'",
                e.lineNum,
                e.pos
            );
        }
        return t.type;
    }

    public Type visit(ArrayExpression e) throws SemanticException {
        Type elemType = this.typeCheckArrayExpression(e.id, e.index, e.lineNum, e.pos);
        // return the type of the array elements
        return elemType;
    }

    public Type visit(ParenExpression e) throws SemanticException {
        // must evaluate expression wrapped in parentheses
        return e.e.accept(this);
    }

    public Type visit(BinaryExpression binExp) throws SemanticException{
        Type left = binExp.left.accept(this);
        Type right = binExp.right.accept(this);

        String op = binExp.getOperator();
        Type result = OperandTypeRules.getTypeOfResult(op, left, right);
        if (result == null) {
            throw new SemanticException(
                "Binary operator '"+op+"' does not support types: '"+left+"' and '"+right+"'",
                binExp.lineNum,
                binExp.pos
            );
        }
        return result;
    }

    public Type visit(LiteralExpression e) {
        return e.type;
    }

    // returns true if 'void main' function, false if not called 'main'
    // o.w. (i.e. when called 'main' but not of type 'void') throws exception
    private boolean isVoidMain(FunctionDecl funcDecl) throws SemanticException {
        // TODO consider refactoring for less hacky type check
        if (funcDecl.id.toString().equals("main")) {
            // 'main' function must be of type 'void'
            if (!funcDecl.returnType.toString().equals("void")) {
                throw new SemanticException(
                    "Found 'main' function not of type 'void'",
                    funcDecl.id.getLineNumber(),
                    funcDecl.id.getLinePos()
                );
            }
            // 'void main' is not allowed to take any parameters
            if (funcDecl.params.size() != 0) {
                throw new SemanticException(
                    "Found parameters in void main function.",
                    funcDecl.id.getLineNumber(),
                    funcDecl.id.getLinePos()
                );
            }
            return true;
        }
        return false;
    }

    // returns element type
    private Type typeCheckArrayExpression(Identifier id, Expression index, int lineNum, int pos) throws SemanticException {
        // make sure id exists
        TypeNode t = this.varEnv.lookup(id.name);
        if (t == null) {
            throw new SemanticException(
                "Could not recognize identifier '"+id+"'",
                lineNum,
                pos
            );

        // make sure referenced id is an aggregate type
        } else if (!(t.type instanceof ArrayType)) {
            throw new SemanticException(
                "Cannot index into identifier of non-aggregate type '"+t.type+"'",
                lineNum,
                pos
            );
        }

        Type indexType = index.accept(this);
        // make sure the index expression is type 'int'
        if (!indexType.toString().equals("int")) {
            throw new SemanticException(
                "Cannot index into identifier with index of type '"+indexType+"'",
                index.lineNum,
                index.pos
            );
        }
        return t.type.getElementType();
    }

    private Type getType(Function f) {
        return f.funcDecl.returnType.type;
    }

    // TODO try generics to collapse into single function
    private String getEnvironmentKey(FunctionDecl funcDecl) {
        return funcDecl.id.toString();
    }

    private String getEnvironmentKey(VariableDeclaration varDecl) {
        return varDecl.id.toString();
    }

    private String getEnvironmentKey(FormalParameter param) {
        return param.id.toString();
    }
}

