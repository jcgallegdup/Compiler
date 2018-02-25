package AST;

import Type.Type;

public class IdentifierExpression extends Expression {
    Identifier id;
    int lineNumber, linePos;

    public IdentifierExpression(Identifier id, int lineNumber, int linePos) {
        this.id = id;
        this.lineNumber = lineNumber;
        this.linePos = linePos;
    }

    public Type accept(TypeCheckVisitor v) throws SemanticException{
        return v.visit(this);
    }

    public String toString() {
        return this.id.toString();
    }
}
