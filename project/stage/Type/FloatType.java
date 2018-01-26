package Type;

import AST.Visitor;

public class FloatType extends Type {
    public FloatType () { }

    public String toShortString () {
        return "float";
    }

    // Possibly misleading name? Should me "is()" instead?
    public boolean equals (Type type) {
        return (type instanceof FloatType);
    }

    public void accept (Visitor v) {
        v.visit(this);
    }
}
