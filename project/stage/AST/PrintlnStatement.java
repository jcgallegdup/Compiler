package AST;

public class PrintlnStatement extends Statement {
    Expression expr;

    public PrintlnStatement(Expression expr) {
        this.expr = expr;
    }

    public String toString() {
        return "println " + expr.toString() + ";";
    }
}
