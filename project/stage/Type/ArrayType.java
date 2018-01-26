package Type;

import AST.Visitor;

public class ArrayType extends Type {
    Type elementType;
    int size;

    public ArrayType (Type t, int size) {
        this.elementType = t;
        this.size = size;
    }

    public String toShortString () {
        return this.elementType.toShortString() + "[" + this.size + "]";
    }

    public Type getElementType() {
        return this.elementType;
    }

    // Possibly misleading name? Should me "is()" instead?
    public boolean equals (Type type) {
        if (type instanceof ArrayType) {
            ArrayType arr = (ArrayType) type;
            return arr.getElementType().equals(this.elementType);
        } else {
            return false;
        }
    }

    public void accept (Visitor v) {
        v.visit(this);
    }
}
