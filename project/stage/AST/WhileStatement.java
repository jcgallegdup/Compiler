package AST;

public class WhileStatement extends Statement {
    Expression cond;
    StatementBlock loopBody;

    public WhileStatement(Expression cond, StatementBlock loopBody) {
        this.cond = cond;
        this.loopBody = loopBody;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public String toString() {
        return "while (" + this.cond.toString() + ")" + this.loopBody.toString();
    }
}
