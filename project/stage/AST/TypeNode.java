package AST;

import Type.*;

public class TypeNode {
    Type type;

    public TypeNode (Type type) {
        this.type = type;
    }

    public void accept (Visitor v) {
        v.visit(this);
        System.out.println("Visiting: " + this);
    }

    public String toString () {
        return this.type.toShortString();
    }
}
