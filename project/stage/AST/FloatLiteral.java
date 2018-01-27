package AST;

public class FloatLiteral extends Expression {
    float val;
    public FloatLiteral(float val) {
        this.val = val;
    }

    public String toString() {
        return Float.toString(this.val);
    }
}
