package AST;

public class ArrayAssignmentStatement extends Statement {
    Identifier id;
    Expression index;
    Expression expr;

    public ArrayAssignmentStatement(Identifier id, Expression index, Expression expr) {
        this.id = id;
        this.index= index;
        this.expr = expr;
    }

    public String toString() {
        return id.toString() + "[" + index.toString() + "] = " + expr.toString() + ";";
    }
}
