package IR;

import IR.IR2Jasmin;

public class IROperand extends IRExpression {
    Temp operand;

    public IROperand(Temp operand) {
        this.operand = operand;
    }

    public void accept(IR2Jasmin v) {
        v.visit(this);
    }

    public String toString() {
        return this.operand.toString();
    }
}