package AST;

public class AddExpression extends BinaryExpression {
    public AddExpression(Expression left, Expression right) {
        super(left, right);
    }

    public char getOperator() {
        return '+';
    }
}
