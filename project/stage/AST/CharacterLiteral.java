package AST;

public class CharacterLiteral extends Expression {
    char val;
    public CharacterLiteral(char val) {
        this.val = val;
    }

    public String toString() {
        return "'" + Character.toString(this.val) + "'";
    }
}
