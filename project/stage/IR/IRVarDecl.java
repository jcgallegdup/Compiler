package IR;

import IR.IR2Jasmin;
import Type.*;

public class IRVarDecl extends IRInstruction {
    Temp var;
    Type type;

    public IRVarDecl(Temp var) {
        this.var = var;
        this.type = var.type;
    }

    public void accept(IR2Jasmin v) {
        v.visit(this);
    }

    public String toString() {
        return "TEMP " + this.var.id + ":" + AST2IRHelper.getIRTypeStr(type) + ";";
    }
}