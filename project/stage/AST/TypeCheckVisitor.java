package AST;

import Type.*;

public class TypeCheckVisitor {
    Environment<String, Function> funcEnv;
    Environment<String, Identifier> varEnv;

    public TypeCheckVisitor() {
        this.funcEnv = new Environment<String, Function>();
        this.varEnv = new Environment<String, Identifier>();
    }

    public void visit(Program p) throws SemanticException {
        boolean containsMainFunction = false;
        // construct function environment before evaluating function bodies
        funcEnv.beginScope();
        for (Function f : p.functionList) {
            String funcEnvKey = getEnvironmentKey(f.funcDecl);
            if (funcEnv.inCurrentScope(funcEnvKey)) {
                throw new SemanticException(
                    "Duplicate function declaration found",
                    f.funcDecl.id.getLineNumber(),
                    f.funcDecl.id.getLinePos()
                );
            }
            funcEnv.add(funcEnvKey, f);

            if (isVoidMain(f.funcDecl)) {
                containsMainFunction = true;
            }
        }

        // throw SemanticException if not containsMainFunction
        if (!containsMainFunction) {
            // TODO determine what line and pos to provide
            throw new SemanticException(
                "Could not find function with name 'main' and type 'void'.",
                0, 0
            );
        }

        // now check each function body
        for (Function f : p.functionList) {
            f.accept(this);
        }
        System.out.println("Finished type checking the program.");
        funcEnv.dumpEnv("Function Env:");
        varEnv.dumpEnv("Variable Env:");
    }

    public void visit(Function f) throws SemanticException {
        f.funcDecl.accept(this);
        f.funcBody.accept(this);
    }

    public void visit(FunctionDecl f) throws SemanticException {
    }

    public void visit(FunctionBody f) throws SemanticException {
    }

    private boolean isVoidMain(FunctionDecl funcDecl) {
        // TODO consider refactoring for less hacky type check
        boolean returnsVoid = funcDecl.returnType.toString().equals("void");
        boolean isCalledMain = funcDecl.id.name.equals("main");
        return isCalledMain && returnsVoid;
    }

    // TODO verify that params are not needed to uniquely id
    private String getEnvironmentKey(FunctionDecl funcDecl) {
        // e.g. "void:main"
        return funcDecl.returnType.toString() + ":" + funcDecl.id.toString();
    }

    private String getEnvironmentKey(VariableDeclaration varDecl) {
        // e.g. "int:x"
        return varDecl.type.toString() + ":" + varDecl.id.toString();
    }
}

