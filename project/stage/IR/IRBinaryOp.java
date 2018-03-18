package IR;

import IR.AST2IRHelper;
import Type.*;

public class IRBinaryOp extends IRExpression {
    Temp left, right;
    Ops op;
    Type type;

    public enum Ops {
        GREATER_THAN,
        EQUALS,
        ADD,
        SUB,
        MULT
    }

    public IRBinaryOp(Temp left, Temp right, Type resultType, Ops op) {
        this.left = left;
        this.right = right;
        this.type = resultType;
        this.op = op;
    }

    // TODO: update this constructor after creating a better representation of binary operators
    public IRBinaryOp(Temp left, Temp right, Type resultType, String opStr) {
        this.left = left;
        this.right = right;
        this.type = resultType;
        this.op = getOpEnum(opStr);
    }

    public String toString() {
        return this.left + " " + AST2IRHelper.getIRTypeStr(this.type) + this.getOpStr(this.op) + " " + this.right;
    }

    private Ops getOpEnum(String opStr) {
        Ops op;
        switch(opStr) {
            case "<":   op = Ops.GREATER_THAN;  break;
            case "==":  op = Ops.EQUALS;        break;
            case "+":   op = Ops.ADD;           break;
            case "-":   op = Ops.SUB;           break;
            case "*":   op = Ops.MULT;          break;
            default:    op = null;              break;
        }
        return op;
    }

    private String getOpStr(Ops op) {
        String opStr;
        switch(op) {
            case GREATER_THAN:  opStr = "<"; break;
            case EQUALS:        opStr = "=="; break;
            case ADD:           opStr = "+"; break;
            case SUB:           opStr = "-"; break;
            case MULT:          opStr = "*"; break;
            default:            opStr = null; break;
        }
        return opStr;
    }
}