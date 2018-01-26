package Type;

import AST.Visitor;

public class VoidType extends Type {
    public VoidType () { }

    public String toShortString () {
        return "void";
    }

    // Possibly misleading name? Should me "is()" instead?
    public boolean equals (Type type) {
        return (type instanceof VoidType);
    }

    public void accept (Visitor v) {
        v.visit(this);
    }
}
