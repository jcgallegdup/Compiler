package Type;

import AST.Visitor;

public class StringType extends Type {
    public StringType () { }

    public String toString() {
        return "string";
    }

    // Possibly misleading name? Should me "is()" instead?
    public boolean equals (Type type) {
        return (type instanceof StringType);
    }

    public void accept (Visitor v) {
        v.visit(this);
    }
}
