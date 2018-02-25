package AST;

import Type.Type;

public class LiteralExpression<T> extends Expression {
    Type type;
    T val;

    public LiteralExpression(Type type, T val, int lineNum, int linePos) {
        super(lineNum, linePos);
        this.type = type;
        this.val = val;
    }

    public Type accept(TypeCheckVisitor v) {
        return v.visit(this);
    }

    public String toString() {
        return val.toString();
    }
}
