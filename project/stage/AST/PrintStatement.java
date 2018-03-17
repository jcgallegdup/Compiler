package AST;

import IR.IRGenerator;

public class PrintStatement extends Statement {
    public Expression expr;

    public PrintStatement(Expression expr) {
        this.expr = expr;
    }

    public void accept(IRGenerator v) {
        v.visit(this);
    }

    public void accept(TypeCheckVisitor v) throws SemanticException {
        v.visit(this);
    }

    public String toString() {
        return "print " + expr.toString() + ";";
    }
}
