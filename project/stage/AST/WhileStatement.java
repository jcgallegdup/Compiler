package AST;

import IR.IRGenerator;
import Type.Type;

public class WhileStatement extends Statement {
    public Expression cond;
    public StatementBlock loopBody;

    public WhileStatement(Expression cond, StatementBlock loopBody) {
        this.cond = cond;
        this.loopBody = loopBody;
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
        return "while (" + this.cond.toString() + ")" + this.loopBody.toString();
    }
}
