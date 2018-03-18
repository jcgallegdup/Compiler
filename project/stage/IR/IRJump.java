package IR;

import IR.IRInstruction;

public class IRJump extends IRInstruction {
    IRLabel label;

    public IRJump(IRLabel label) {
        this.label = label;
    }

    public String toString() {
        return "GOTO " + label.labelStr() + ";";
    }
}