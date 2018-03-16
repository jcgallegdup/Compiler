package IR;

import java.util.LinkedList;
import java.util.List;

public class IRFunction {
    // TODO verify this should be a str
    String name;

    // TODO consult someone else regarding how to represent the fun signature
    String type;
    List<String> paramTypes;

    List<IRInstruction> instrs;

    public IRFunction() {
        this.instrs = new LinkedList<IRInstruction>();
    }

    public String getDeclaration() {
        return "FUNC " + name + " ( TODO ) TODO ";
    }

    // TODO add methods to interact with this.temps

    // TODO add methods to interact with this.instrs
}