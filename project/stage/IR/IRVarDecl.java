package IR;

import Type.*;

public class IRVarDecl extends IRInstruction {
    Temp var;
    Type type;

    public IRVarDecl(Temp var) {
        this.var = var;
        this.type = var.type;
    }

    public String toString() {
        return "TEMP " + this.var.id + ":" + AST2IRHelper.getIRTypeStr(type) + ";";
    }
}