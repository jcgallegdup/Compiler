package IR;

import IR.IR2Jasmin;
import IR.IRInstruction;

public class IRJump extends IRInstruction {
    IRLabel label;

    public IRJump(IRLabel label) {
        this.label = label;
    }

    public void accept(IR2Jasmin v) {
        v.visit(this);
    }

    public String toString() {
        return "GOTO " + label.labelStr() + ";";
    }
}