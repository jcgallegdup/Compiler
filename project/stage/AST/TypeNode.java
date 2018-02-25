package AST;

import Type.*;

public class TypeNode {
    Type type;
    private int lineNumber, linePos;

    public TypeNode (Type type, int lineNumber, int linePos) {
        this.type = type;
        this.lineNumber = lineNumber;
        this.linePos = linePos;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getLinePos() {
        return linePos;
    }

    public void accept (Visitor v) {
        v.visit(this);
        System.out.println("Visiting: " + this);
    }

    public String toString () {
        return this.type.toString();
    }
}
