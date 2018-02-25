package AST;

import Type.Type;

public class FunctionCallExpression extends Expression {
    Identifier id;
    ExpressionList args;

    public FunctionCallExpression(Identifier id, ExpressionList args) {
        this.id = id;
        this.args = args;
    }

    public Type accept(TypeCheckVisitor v) throws SemanticException {
        return v.visit(this);
    }

    public String toString() {
        return this.id.toString() + "(" + args.toString() + ")";
    }
}
