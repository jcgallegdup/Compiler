package AST;

import IR.IRGenerator;

public class ScalarAssignmentStatement extends Statement {
    public Identifier id;
    public Expression expr;

    public ScalarAssignmentStatement(Identifier id, Expression expr) {
        this.id = id;
        this.expr = expr;
    }

    public void accept(IRGenerator v) {
        v.visit(this);
    }

    public void accept(TypeCheckVisitor v) throws SemanticException {
        v.visit(this);
    }

    public String toString() {
        return id.toString() + " = " + expr.toString() + ";";
    }
}
