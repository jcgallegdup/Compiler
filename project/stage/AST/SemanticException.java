package AST;

public class SemanticException extends Exception {
    String message;
    int line;
    int pos;

    public SemanticException(String msg, int line, int pos) {
        this.message = msg;
        this.line = line;
        this.pos = pos;
    }

    public String toString() {
        String s = "Error:" + this.line + "(line):" + this.pos + "(position): " + this.message;
        return s;
    }
}