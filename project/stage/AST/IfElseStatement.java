package AST;

import IR.IRGenerator;
import Type.Type;

public class IfElseStatement extends Statement {
    public Expression cond;
    public StatementBlock ifBlock, elseBlock;

    public IfElseStatement(Expression cond, StatementBlock ifBlock, StatementBlock elseBlock) {
        this.cond = cond;
        this.ifBlock = ifBlock;
        this.elseBlock = elseBlock;
    }

    public IfElseStatement(Expression cond, StatementBlock ifBlock) {
        this.cond = cond;
        this.ifBlock = ifBlock;
        this.elseBlock = null;
    }

    public void accept(IRGenerator v) {
        v.visit(this);
    }

    public void accept(TypeCheckVisitor v) throws SemanticException {
        v.visit(this);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public String toString() {
        return this.ifToString() + "\n" + this.ifBlock.toString() +
            this.elseToString() + "\n" + this.elseBlock.toString();
    }

    private String ifToString() {
        return "if (" + this.cond.toString() + ")";
    }

    private String elseToString() {
        return this.elseBlock == null? "" : "else";
    }
}
