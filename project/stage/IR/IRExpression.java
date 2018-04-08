package IR;

public abstract class IRExpression {
    public abstract String toString();

    public void accept(IR2Jasmin v) {
        v.visit(this);
    }
}