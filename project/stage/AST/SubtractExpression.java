package AST;

public class SubtractExpression extends BinaryExpression {
    public SubtractExpression(Expression left, Expression right) {
        super(left, right);
    }

    public String getOperator() {
        return "-";
    }
}
