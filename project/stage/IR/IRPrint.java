package IR;

import Type.*;

public class IRPrint extends IRInstruction {
    Temp var;
    Type type;

    public IRPrint(Temp var) {
        this.var = var;
        this.type = var.type;
    }

    public String toString() {
        return "PRINT" + IRTypeHelper.getIRTypeStr(type) + " " + this.var.toString() + ";";
    }
}