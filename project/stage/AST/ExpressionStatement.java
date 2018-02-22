package AST;

public class ExpressionStatement extends Statement {
    Expression expr;

    public ExpressionStatement(Expression expr) {
        this.expr = expr;
    }

    public void accept(TypeCheckVisitor v) {
        v.visit(this);
    }

    public String toString() {
        return expr.toString() + ";";
    }
}
