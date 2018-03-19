package AST;

import IR.IRGenerator;
import IR.Temp;
import Type.Type;

public class ParenExpression extends Expression {
    public Expression e;

    public ParenExpression(Expression e) {
        super(e.lineNum, e.pos);
        this.e = e;
    }

    public Temp accept(IRGenerator v) {
        return v.visit(this);
    }

    public Type accept(TypeCheckVisitor v) throws SemanticException {
        return v.visit(this);
    }

    public String toString() {
        return "(" + this.e.toString() + ")";
    }
}
