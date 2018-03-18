package AST;

import IR.IRGenerator;

public class ExpressionStatement extends Statement {
    public Expression expr;

    public ExpressionStatement(Expression expr) {
        this.expr = expr;
    }

    public void accept(IRGenerator v) {
        v.visit(this);
    }

    public void accept(TypeCheckVisitor v) throws SemanticException {
        v.visit(this);
    }

    public String toString() {
        return expr.toString() + ";";
    }
}
