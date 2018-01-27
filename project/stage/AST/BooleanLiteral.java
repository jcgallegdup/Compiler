package AST;

public class BooleanLiteral extends Expression {
    boolean val;
    public BooleanLiteral(boolean val) {
        this.val = val;
    }

    public String toString() {
        return Boolean.toString(this.val);
    }
}
