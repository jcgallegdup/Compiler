package IR;

import java.util.LinkedList;
import java.util.List;

import IR.AST2IRHelper;
import IR.IR2Jasmin;
import IR.IRInstruction;
import IR.IRProgram;
import IR.IRVarDecl;
import Type.*;

public class IRFunction {
    IRProgram prog;
    // TODO verify this should be a str
    String name;
    // TODO consult someone else regarding how to represent the fun signature
    Type returnType;
    List<Type> paramTypes;
    List<IRVarDecl> varDecls;
    List<IRInstruction> instrs;

    public IRFunction(IRProgram prog, String name, Type returnType, List<Type> paramTypes) {
        this.prog = prog;
        this.name = name;
        this.returnType = returnType;
        this.paramTypes = paramTypes;
        this.varDecls = new LinkedList<IRVarDecl>();
        this.instrs = new LinkedList<IRInstruction>();
    }

    public String getDeclaration() {
        String typeStr = "(";
        String separator = "";
        for (Type t : this.paramTypes) {
            typeStr += separator + AST2IRHelper.getIRTypeStr(t);
            separator = " ";
        }
        typeStr += ")";
        return "FUNC " + this.name + " " + typeStr + " " + AST2IRHelper.getIRTypeStr(returnType);
    }

    // TODO add methods to interact with this.temps

    public void addVarDecl(IRVarDecl instr) {
        this.varDecls.add(instr);
    }

    public void addInstr(IRInstruction instr) {
        this.instrs.add(instr);
    }

    public List<IRInstruction> getAllInstrs() {
        // create new list of IR instrs with declarations at the front
        List<IRInstruction> allInstrs = new LinkedList<IRInstruction>();
        allInstrs.addAll(this.varDecls);
        allInstrs.addAll(this.instrs);
        return allInstrs;
    }

    public void accept(IR2Jasmin v) {
        v.visit(this);
    }
}