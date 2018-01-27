package AST;

public abstract class BinaryExpression extends Expression {
    Expression left, right;

    public abstract String getOperator();

    public BinaryExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public String toString() {
        return this.left.toString() + " " + this.getOperator() + " " + this.right.toString();
    }
}
