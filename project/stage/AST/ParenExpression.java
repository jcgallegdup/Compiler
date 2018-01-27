package AST;

public class ParenExpression extends Expression {
    Expression e;

    public ParenExpression(Expression e) {
        this.e = e;
    }

    public String toString() {
        return "(" + this.e.toString() + ")";
    }
}
