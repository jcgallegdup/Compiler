package AST;

public class PrintStatement extends Statement {
    Expression expr;

    public PrintStatement(Expression expr) {
        this.expr = expr;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public String toString() {
        return "print " + expr.toString() + ";";
    }
}
