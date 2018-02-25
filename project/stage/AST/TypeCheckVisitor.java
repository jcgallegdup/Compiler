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
            if (!this.funcEnv.add(funcEnvKey, f)) {
                throw new SemanticException(
                    "Found duplicate function declaration",
                    f.funcDecl.id.getLineNumber(),
                    f.funcDecl.id.getLinePos()
                );
            }
            this.funcEnv.add(funcEnvKey, f);
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
        this.funcEnv.dumpEnv();
    }

    public void visit(Function f) throws SemanticException {
        // init with a pointer to its enclosing function
        this.varEnv = new Environment<String, TypeNode, Function>(f);

        // add all param/var declarations to new env
        addVarsToEnv(f.funcDecl.params, f.funcBody.varDecls);

        f.funcBody.accept(this);
        this.varEnv.dumpEnv();
    }

    // TODO: collapse duplicate loops by treating params & varDecls as similar objects
    private void addVarsToEnv(FormalParameterList params, Iterable<VariableDeclaration> varDecls) throws SemanticException {
        for (FormalParameter param : params) {
            if (param.type.toString().equals("void")) {
                throw new SemanticException(
                    "Found formal function param of type void",
                    param.id.getLineNumber(),
                    param.id.getLinePos()
                );
            }
            if (!this.varEnv.add(getEnvironmentKey(param), param.type)) {
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
            if (!this.varEnv.add(getEnvironmentKey(varDecl), varDecl.type)) {
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

    public void visit(ExpressionStatement s) throws SemanticException {
        Type type = s.expr.accept(this);
        System.out.println(s.expr.toString()+":"+type);
    }

    // will be removed once method in class is made abstract
    public Type visit(Expression e) {
        return null;
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

