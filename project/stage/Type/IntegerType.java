package Type;

import AST.Visitor;

public class IntegerType extends Type {
    public IntegerType () {
    }

    public String toShortString () {
        return "int";
    }

    // Possibly misleading name? Should me "is()" instead?
    public boolean equals (Object o) {
        return (o instanceof IntegerType);
    }

    public void accept (Visitor v) {
        v.visit(this);
    }
}
