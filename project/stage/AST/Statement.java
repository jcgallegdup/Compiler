package AST;

public abstract class Statement {
    public abstract String toString();

    // TODO make this abstract
    public void accept(TypeCheckVisitor v) throws SemanticException {
        v.visit(this);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
