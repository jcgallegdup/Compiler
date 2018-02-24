package AST;

import Type.Type;

public abstract class Expression {

    public abstract String toString();

    // TODO make this abstract
    public Type accept(TypeCheckVisitor v) throws SemanticException {
        return v.visit(this);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
