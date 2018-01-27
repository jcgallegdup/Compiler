package AST;

public abstract class Statement {
    public abstract String toString();

    public void accept(Visitor v) {
        v.visit(this);
    }
}
