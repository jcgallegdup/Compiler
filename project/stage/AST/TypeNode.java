package AST;

import Type.*;

public class TypeNode {
    Type type;
    int lineNumber, lineOffset;

    public TypeNode (Type type, int lineNumber, int lineOffset) {
        this.type = type;
        this.lineNumber = lineNumber;
        this.lineOffset = lineOffset;
    }

    public void accept (Visitor v) {
        v.visit(this);
        System.out.println("Visiting: " + this);
    }

    public String toString () {
        return this.type.toShortString();
    }
}
