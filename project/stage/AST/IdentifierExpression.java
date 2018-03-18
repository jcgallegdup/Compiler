package AST;

import IR.IRGenerator;
import Type.Type;
import IR.Temp;

public class IdentifierExpression extends Expression {
    public Identifier id;
    int lineNumber, linePos;

    public IdentifierExpression(Identifier id, int lineNumber, int linePos) {
        super(id.getLineNumber(), id.getLinePos());
        this.id = id;
        this.lineNumber = lineNumber;
        this.linePos = linePos;
    }

    public Temp accept(IRGenerator v) {
        return v.visit(this);
    }

    public Type accept(TypeCheckVisitor v) throws SemanticException{
        return v.visit(this);
    }

    public String toString() {
        return this.id.toString();
    }
}
