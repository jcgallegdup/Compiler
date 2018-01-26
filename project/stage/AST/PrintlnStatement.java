package AST;

public class PrintlnStatement extends Statement {
    Expression expr;

    public PrintlnStatement(Expression expr) {
        this.expr = expr;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public String toString() {
        return "println " + expr.toString() + ";";
    }
}
