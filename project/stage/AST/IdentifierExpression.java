package AST;

public class IdentifierExpression extends Expression {
    Identifier id;
    int lineNumber, lineOffset;

    public IdentifierExpression(Identifier id, int lineNumber, int lineOffset) {
        this.id = id;
        this.lineNumber = lineNumber;
        this.lineOffset = lineOffset;
    }

    public String toString() {
        return this.id.toString();
    }
}
