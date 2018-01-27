package AST;

public abstract class Expression {

    public abstract String toString();

    public void accept(Visitor v) {
        v.visit(this);
    }
}
