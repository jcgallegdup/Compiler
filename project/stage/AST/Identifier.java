package AST;

public class Identifier {
    String name;
    int lineNumber, lineOffset;

    public Identifier (String name, int lineNumber, int lineOffset) {
        this.name = name;
        this.lineNumber = lineNumber;
        this.lineOffset = lineOffset;
    }

    public int getLineNumber(){
        return this.lineNumber;
    }

    public int getLineOffset(){
        return this.lineOffset;
    }

    public void accept (Visitor v) {
        v.visit(this);
        System.out.println("Visiting: " + this);
    }

    public String toString() {
        return name;
    }
}
