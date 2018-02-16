package AST;

public class IdentifierExpression extends Expression {
    Identifier id;
    int lineNumber, linePos;

    public IdentifierExpression(Identifier id, int lineNumber, int linePos) {
        this.id = id;
        this.lineNumber = lineNumber;
        this.linePos = linePos;
    }

    public String toString() {
        return this.id.toString();
    }
}
