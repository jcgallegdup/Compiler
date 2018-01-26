package Type;

import AST.Visitor;

public class BooleanType extends Type {
    public BooleanType () { }

    public String toShortString () {
        return "boolean";
    }

    // Possibly misleading name? Should me "is()" instead?
    public boolean equals (Type type) {
        return (type instanceof BooleanType);
    }

    public void accept (Visitor v) {
        v.visit(this);
    }
}
