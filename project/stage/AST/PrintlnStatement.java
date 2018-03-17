package AST;

import IR.IRGenerator;
import IR.Temp;

public class PrintlnStatement extends Statement {
    public Expression expr;

    public PrintlnStatement(Expression expr) {
        this.expr = expr;
    }

    public void accept(IRGenerator v) {
        v.visit(this);
    }

    public void accept(TypeCheckVisitor v) throws SemanticException {
        v.visit(this);
    }

    public String toString() {
        return "println " + expr.toString() + ";";
    }
}
