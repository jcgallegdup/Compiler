package AST;

public abstract class Expression {
    public abstract void accept(Visitor v);
    public abstract String toString();
}
