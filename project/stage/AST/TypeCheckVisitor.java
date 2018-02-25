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
        this.funcEnv.dumpEnv();

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
        this.varEnv.dumpEnv();

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

    public void visit(ReturnStatement s) throws SemanticException {
        Type expectedType = this.getType(this.varEnv.enclosingScope);
        Type actualType = (s.expr == null? new VoidType() : s.expr.accept(this));
        if (!expectedType.equals(actualType)) {
            // TODO return line number and pos from type
            throw new SemanticException(
                "Type of expression in return statement '"+actualType+"' does not match declared function type '"+expectedType+"'",
                -1, -1
            );
        }
    }

    public void visit(ExpressionStatement s) throws SemanticException {
        Type type = s.expr.accept(this);
        System.out.println(s.expr.toString()+":"+type);
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
                // TODO add line num and pos
                -1, -1
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
                // TODO add line num and pos
                -1, -1
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
                    // TODO get line num and pos from type rather than typenode
                    expectedParams.get(i).type.getLineNumber(),
                    expectedParams.get(i).type.getLinePos()
                );
            }
        }
        return func.funcDecl.returnType.type;
    }

    public Type visit(IdentifierExpression e) throws SemanticException {
        TypeNode t = this.varEnv.lookup(e.id.name);
        if (t == null) {
            // TODO get line number and pos from Type inside TypeNode
            throw new SemanticException(
                "Could not recognize identifier '"+e.id+"'",
                t.getLineNumber(),
                t.getLinePos()
            );
        }
        return t.type;
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
                // TODO: add line num and pos to Type class
                // rightType.getLineNumber(),
                // rightType.getLinePos()
                -1, -1
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

