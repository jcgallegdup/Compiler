package AST;

import Type.Type;

public class ArrayExpression extends Expression {
    Identifier id;
    Expression index;

    public ArrayExpression(Identifier id, Expression index) {
        super(id.getLineNumber(), id.getLinePos());
        this.id = id;
        this.index = index;
    }

    public Type accept(TypeCheckVisitor v) throws SemanticException{
        return v.visit(this);
    }

    public String toString() {
        return this.id.toString() + "[" + this.index.toString() + "]";
    }
}
