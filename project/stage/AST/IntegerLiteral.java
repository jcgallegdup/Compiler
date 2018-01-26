package AST;

public class IntegerLiteral extends Expression {
    int val;
    public IntegerLiteral(int val) {
        this.val = val;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public String toString() {
        return Integer.toString(this.val);
    }
}
