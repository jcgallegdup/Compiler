package AST;

import IR.IRGenerator;

public abstract class Statement {
    public abstract String toString();

    public void accept(IRGenerator v) {
        v.visit(this);
    }

    public void accept(TypeCheckVisitor v) throws SemanticException {
        v.visit(this);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
