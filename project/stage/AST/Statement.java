package AST;

public abstract class Statement {
    public abstract String toString();

    public void accept(TypeCheckVisitor v) {
        v.visit(this);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
