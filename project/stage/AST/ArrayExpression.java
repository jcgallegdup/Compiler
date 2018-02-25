package AST;

public class ArrayExpression extends Expression {
    Identifier id;
    Expression index;

    public ArrayExpression(Identifier id, Expression index) {
        super(id.getLineNumber(), id.getLinePos());
        this.id = id;
        this.index = index;
    }

    public String toString() {
        return this.id.toString() + "[" + this.index.toString() + "]";
    }
}
