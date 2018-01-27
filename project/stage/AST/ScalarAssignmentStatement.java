package AST;

public class ScalarAssignmentStatement extends Statement {
    Identifier id;
    Expression expr;

    public ScalarAssignmentStatement(Identifier id, Expression expr) {
        this.id = id;
        this.expr = expr;
    }

    public String toString() {
        return id.toString() + " = " + expr.toString() + ";";
    }
}
