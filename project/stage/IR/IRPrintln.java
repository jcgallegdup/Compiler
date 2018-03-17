package IR;

import Type.*;

public class IRPrintln extends IRInstruction {
    Temp var;
    Type type;

    public IRPrintln(Temp var) {
        this.var = var;
        this.type = var.type;
    }

    public String toString() {
        return "PRINTLN" + IRTypeHelper.getIRTypeStr(type) + " " + this.var.toString() + ";";
    }
}