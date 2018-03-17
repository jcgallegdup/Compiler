package AST;

import IR.IRGenerator;

public class Function {
    public FunctionDecl funcDecl;
    public FunctionBody funcBody;

    public Function (FunctionDecl funcDecl, FunctionBody funcBody) {
        this.funcDecl = funcDecl;
        this.funcBody = funcBody;
    }

    public void accept (IRGenerator v) {
        v.visit(this);
    }

    public void accept (TypeCheckVisitor v) throws SemanticException {
        v.visit(this);
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public String toString () {
        return this.funcDecl.toString();
    }
}
