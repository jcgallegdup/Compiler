package AST;

public class StringLiteral extends Expression {
    String val;
    public StringLiteral(String val) {
        this.val = val;
    }

    public String toString() {
        return this.val;
    }
}
