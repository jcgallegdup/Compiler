package AST;

public abstract class Statement {
    public abstract void accept(Visitor v);
    public abstract String toString();
}
