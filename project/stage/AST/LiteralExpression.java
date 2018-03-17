package AST;

import IR.IRGenerator;
import IR.Temp;
import Type.Type;

public class LiteralExpression<T> extends Expression {
    public Type type;
    public T val;

    public LiteralExpression(Type type, T val, int lineNum, int linePos) {
        super(lineNum, linePos);
        this.type = type;
        this.val = val;
    }

    public Temp accept(IRGenerator v) {
        return v.visit(this);
    }

    public Type accept(TypeCheckVisitor v) {
        return v.visit(this);
    }

    public String toString() {
        return val.toString();
    }
}
