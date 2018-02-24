package Type;

import AST.Visitor;

public class IntegerType extends Type {
    public IntegerType () { }

    public String toString() {
        return "int";
    }

    // Possibly misleading name? Should me "is()" instead?
    public boolean equals (Type type) {
        return (type instanceof IntegerType);
    }

    public void accept (Visitor v) {
        v.visit(this);
    }
}
