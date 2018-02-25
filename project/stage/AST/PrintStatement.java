package AST;

public class PrintStatement extends Statement {
    Expression expr;

    public PrintStatement(Expression expr) {
        this.expr = expr;
    }

    public void accept(TypeCheckVisitor v) throws SemanticException {
        v.visit(this);
    }

    public String toString() {
        return "print " + expr.toString() + ";";
    }
}
