package AST;

public class ScalarAssignmentStatement extends Statement {
    Identifier id;
    Expression expr;

    public ScalarAssignmentStatement(Identifier id, Expression expr) {
        this.id = id;
        this.expr = expr;
    }

    public void accept(TypeCheckVisitor v) throws SemanticException {
        v.visit(this);
    }

    public String toString() {
        return id.toString() + " = " + expr.toString() + ";";
    }
}
