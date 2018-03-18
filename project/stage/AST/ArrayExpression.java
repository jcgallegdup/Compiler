package AST;

import IR.IRGenerator;
import IR.Temp;
import Type.Type;

public class ArrayExpression extends Expression {
    public Identifier id;
    public Expression index;

    public ArrayExpression(Identifier id, Expression index) {
        super(id.getLineNumber(), id.getLinePos());
        this.id = id;
        this.index = index;
    }

    public Temp accept(IRGenerator v) {
        return v.visit(this);
    }

    public Type accept(TypeCheckVisitor v) throws SemanticException{
        return v.visit(this);
    }

    public String toString() {
        return this.id.toString() + "[" + this.index.toString() + "]";
    }
}
