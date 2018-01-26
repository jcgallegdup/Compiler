package AST;

public class ReturnStatement extends Statement {
    Expression expr;

    public ReturnStatement(Expression expr) {
        this.expr = expr;
    }

    public ReturnStatement() {
        this.expr = null;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public String toString() {
        return "return " + expr.toString() + ";";
    }
}
