package AST;

public class MultExpression extends Expression {
    Expression left, right;

    public MultExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public String toString() {
        return this.left.toString() + " * " + this.right.toString();
    }
}
