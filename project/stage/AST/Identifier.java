package AST;

public class Identifier {
    String name;
    int lineNumber, linePos;

    public Identifier (String name, int lineNumber, int linePos) {
        this.name = name;
        this.lineNumber = lineNumber;
        this.linePos = linePos;
    }

    public int getLineNumber(){
        return this.lineNumber;
    }

    public int getLinePos(){
        return this.linePos;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public String toString() {
        return name;
    }
}
