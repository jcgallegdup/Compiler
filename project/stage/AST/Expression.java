package AST;

import Type.Type;

public abstract class Expression {
    int lineNum, pos;

    public Expression(int lineNum, int pos) {
        this.lineNum = lineNum;
        this.pos = pos;
    }

    public abstract String toString();

    // TODO make this abstract
    public Type accept(TypeCheckVisitor v) throws SemanticException {
        return v.visit(this);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
