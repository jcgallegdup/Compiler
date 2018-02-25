package AST;

public class PrintlnStatement extends Statement {
    Expression expr;

    public PrintlnStatement(Expression expr) {
        this.expr = expr;
    }

    public void accept(TypeCheckVisitor v) throws SemanticException {
        v.visit(this);
    }

    public String toString() {
        return "println " + expr.toString() + ";";
    }
}
