package AST;

public class EqualityExpression extends BinaryExpression {
    public EqualityExpression(Expression left, Expression right) {
        super(left, right);
    }

    public String getOperator() {
        return "==";
    }
}
