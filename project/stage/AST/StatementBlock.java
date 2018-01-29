package AST;

import java.util.Vector;

public class StatementBlock {
    Vector<Statement> statements;
    private final int indentSize = 4;

    public StatementBlock() {
        this.statements = new Vector<Statement>();
    }

    public void addStatement(Statement s) {
        statements.add(s);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public String toString() {
        String blockStr = "{\n";
        String indent = new String(new char[this.indentSize]).replace("\0", " ");
        for (Statement s : this.statements) {
            blockStr += indent + s.toString() + "\n";
        }
        return blockStr + "}";
    }
}
