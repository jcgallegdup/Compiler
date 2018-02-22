package AST;

import Type.*;

public abstract class Expression {

    public abstract String toString();

    public Type accept(TypeCheckVisitor v) {
        return v.visit(this);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
