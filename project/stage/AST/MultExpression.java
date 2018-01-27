package AST;

public class MultExpression extends BinaryExpression {
    public MultExpression(Expression left, Expression right) {
        super(left, right);
    }

    public String getOperator() {
        return "*";
    }
}
