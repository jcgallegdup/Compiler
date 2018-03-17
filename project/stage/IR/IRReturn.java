package IR;

import Type.*;

public class IRReturn extends IRInstruction {
    Temp var;

    public IRReturn(Temp var) {
        this.var = var;
    }

    // temp var is optional e.g. don't need when return type is void
    public IRReturn() {
        this.var = null;
    }

    public String toString() {
        if (this.var == null) {
            return "RETURN ;";
        } else {
            return "RETURN " + this.var.toString() + ";";
        }
    }
}