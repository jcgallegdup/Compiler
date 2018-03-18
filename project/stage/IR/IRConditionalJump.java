package IR;

import IR.IRInstruction;

public class IRConditionalJump extends IRJump {
    Temp cond;

    public IRConditionalJump(IRLabel label, Temp cond) {
        super(label);
        this.cond = cond;
    }

    public String toString() {
        return "IF " + this.cond + " GOTO " + label.labelStr() + ";";
    }
}