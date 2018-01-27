package AST;

public class FunctionCallExpression extends Expression {
    Identifier id;
    ExpressionList args;

    public FunctionCallExpression(Identifier id, ExpressionList args) {
        this.id = id;
        this.args = args;
    }

    public String toString() {
        return this.id.toString() + "(" + args.toString() + ")";
    }
}
