package IR;

import IR.AST2IRHelper;
import Type.*;

public class IRUnaryOp extends IRExpression {
    Temp operand;
    Ops op;
    Type type;

    public enum Ops {
        NEGATE
    }

    public IRUnaryOp(Temp operand, Ops op) {
        this.operand = operand;
        this.op = op;
        this.type = operand.type;
    }

    public void accept(IR2Jasmin v) {
        v.visit(this);
    }

    public String toString() {
        return AST2IRHelper.getIRTypeStr(this.type) + this.getOpStr(this.op) + " " + this.operand;
    }

    private String getOpStr(Ops op) {
        String opStr;
        switch(op) {
            case NEGATE: opStr = "!"; break;
            default:     opStr = null; break;
        }
        return opStr;
    }
}