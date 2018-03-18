package AST;

import IR.IRGenerator;

public class ArrayAssignmentStatement extends Statement {
    public Identifier id;
    public Expression index;
    public Expression expr;

    public ArrayAssignmentStatement(Identifier id, Expression index, Expression expr) {
        this.id = id;
        this.index= index;
        this.expr = expr;
    }

    public void accept(IRGenerator v) {
        v.visit(this);
    }

    public void accept(TypeCheckVisitor v) throws SemanticException {
        v.visit(this);
    }

    public String toString() {
        return id.toString() + "[" + index.toString() + "] = " + expr.toString() + ";";
    }
}
