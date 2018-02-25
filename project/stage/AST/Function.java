package AST;

public class Function {
    FunctionDecl funcDecl;
    FunctionBody funcBody;

    public Function (FunctionDecl funcDecl, FunctionBody funcBody) {
        this.funcDecl = funcDecl;
        this.funcBody = funcBody;
    }

    public void accept (TypeCheckVisitor v) throws SemanticException {
        v.visit(this);
    }

    public void accept (Visitor v) {
        v.visit(this);
        // System.out.println("Visiting: " + this);
    }

    public String toString () {
        return this.funcDecl.toString();
    }
}
