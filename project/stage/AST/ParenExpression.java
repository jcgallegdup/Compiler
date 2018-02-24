package AST;

import Type.Type;

public class ParenExpression extends Expression {
    Expression e;

    public ParenExpression(Expression e) {
        this.e = e;
    }

    public Type accept(TypeCheckVisitor v) throws SemanticException {
        return v.visit(this);
    }

    public String toString() {
        return "(" + this.e.toString() + ")";
    }
}
