package IR;

import Type.*;

public class IRPrint extends IRInstruction {
    Temp var;
    Type type;

    public IRPrint(Temp var) {
        this.var = var;
        this.type = var.type;
    }

    public void accept(IR2Jasmin v) {
        v.visit(this);
    }

    public String toString() {
        return "PRINT" + AST2IRHelper.getIRTypeStr(type) + " " + this.var.toString() + ";";
    }
}