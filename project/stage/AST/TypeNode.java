package AST;

import Type.*;

public class TypeNode {
    Type type;
    int lineNumber, linePos;

    public TypeNode (Type type, int lineNumber, int linePos) {
        this.type = type;
        this.lineNumber = lineNumber;
        this.linePos = linePos;
    }

    public void accept (Visitor v) {
        v.visit(this);
        System.out.println("Visiting: " + this);
    }

    public String toString () {
        return this.type.toShortString();
    }
}
