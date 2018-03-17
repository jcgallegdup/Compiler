package AST;

import IR.IRGenerator;
import IR.Temp;
import Type.Type;

public class FunctionCallExpression extends Expression {
    public Identifier id;
    public ExpressionList args;

    public FunctionCallExpression(Identifier id, ExpressionList args) {
        super(id.getLineNumber(), id.getLinePos());
        this.id = id;
        this.args = args;
    }

    public Temp accept(IRGenerator v) {
        return v.visit(this);
    }

    public Type accept(TypeCheckVisitor v) throws SemanticException {
        return v.visit(this);
    }

    public String toString() {
        return this.id.toString() + "(" + args.toString() + ")";
    }
}
