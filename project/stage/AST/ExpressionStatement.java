package AST;

public class ExpressionStatement extends Statement {
    Expression expr;

    public ExpressionStatement(Expression expr) {
        this.expr = expr;
    }

    public String toString() {
        return expr.toString() + ";";
    }
}
