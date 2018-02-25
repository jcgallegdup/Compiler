package AST;

import Type.Type;

public class ReturnStatement extends Statement {
    Expression expr;

    public ReturnStatement(Expression expr) {
        this.expr = expr;
    }

    public ReturnStatement() {
        this.expr = null;
    }

    public void accept(TypeCheckVisitor v) throws SemanticException {
        v.visit(this);
    }

    public String toString() {
        return "return " + (this.expr == null? "" : expr.toString()) + ";";
    }
}
