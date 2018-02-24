package Type;

import AST.Visitor;

public class CharType extends Type {
    public CharType () { }

    public String toString() {
        return "char";
    }

    // Possibly misleading name? Should me "is()" instead?
    public boolean equals (Type type) {
        return (type instanceof CharType);
    }

    public void accept (Visitor v) {
        v.visit(this);
    }
}
