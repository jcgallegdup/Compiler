package IR;

import java.util.LinkedList;
import java.util.List;

import IR.IRInstruction;
import Type.*;

public class IRFunction {
    // TODO verify this should be a str
    String name;
    // TODO consult someone else regarding how to represent the fun signature
    Type returnType;
    List<Type> paramTypes;
    List<IRInstruction> instrs;

    public IRFunction(String name, Type returnType, List<Type> paramTypes) {
        this.name = name;
        this.returnType = returnType;
        this.paramTypes = paramTypes;
        this.instrs = new LinkedList<IRInstruction>();
    }

    public String getDeclaration() {
        String typeStr = "(";
        String separator = "";
        for (Type t : this.paramTypes) {
            typeStr += separator + IRTypeHelper.getIRTypeStr(t);
            separator = " ";
        }
        typeStr += ")";
        return "FUNC " + this.name + " " + typeStr + " " + IRTypeHelper.getIRTypeStr(returnType);
    }

    // TODO add methods to interact with this.temps

    public void addInstr(IRInstruction instr) {
        this.instrs.add(instr);
    }
}