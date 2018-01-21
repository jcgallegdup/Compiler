package AST;

import Type.*;

public class VariableDeclaration {
    TypeNode type;
    Identifier id;

    public VariableDeclaration (TypeNode type, Identifier id) {
        this.type = type;
        this.id = id;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public String toString () {
        return this.type.toString() + " " + this.id.toString() + ";";
    }
}
