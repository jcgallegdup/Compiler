package AST;

import IR.IRGenerator;
import Type.*;

public class VariableDeclaration {
    public TypeNode type;
    Identifier id;

    public VariableDeclaration (TypeNode type, Identifier id) {
        this.type = type;
        this.id = id;
    }

    public void accept (IRGenerator v) {
        v.visit(this);
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public String toString () {
        return this.type.toString() + " " + this.id.toString() + ";";
    }
}
