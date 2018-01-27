package AST;

public class IdentifierExpression extends Expression {
    Identifier id;

    public IdentifierExpression(Identifier id) {
        this.id = id;
    }

    public String toString() {
        return this.id.toString();
    }
}
