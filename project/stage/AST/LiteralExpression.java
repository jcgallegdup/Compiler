package AST;

import Type.Type;

public class LiteralExpression<T> extends Expression {
    Type type;
    T val;

    public LiteralExpression(Type type, T val) {
        this.type = type;
        this.val = val;
    }

    public LiteralExpression(Type type) {
        this.type = type;
    }

    public Type accept(TypeCheckVisitor v) {
        return v.visit(this);
    }

    public String toString() {
        return val.toString();
    }
}
