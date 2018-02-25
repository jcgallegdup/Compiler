package AST;

import Type.Type;

public abstract class BinaryExpression extends Expression {
    Expression left, right;

    public abstract String getOperator();

    public BinaryExpression(Expression left, Expression right) {
        super(left.lineNum, left.pos);
        this.left = left;
        this.right = right;
    }

    public Type accept(TypeCheckVisitor v) throws SemanticException {
        return v.visit(this);
    }

    public String toString() {
        return this.left.toString() + this.getOperator() + this.right.toString();
    }
}
