package AST;

public class LessThanExpression extends BinaryExpression {
    public LessThanExpression(Expression left, Expression right) {
        super(left, right);
    }

    public String getOperator() {
        return "<";
    }
}
