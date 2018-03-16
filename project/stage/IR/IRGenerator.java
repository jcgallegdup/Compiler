package IR;

import AST.*;
import IR.IRInstruction;
import Type.*;

import java.util.List;
import java.util.LinkedList;

public class IRGenerator {
    IRProgram IRProg;
    IRFunction currIRFunc;

    public IRGenerator() {
        this.IRProg = new IRProgram();
        this.currIRFunc = null;
    }

    public void visit(Program p) {
        // TODO add name to IRProgram
        IRProg.addName("TODO");
        for (Function f : p.functionList) {
            f.accept(this);
        }
    }

    public void visit(Function f) {
        // create new IRFunction
            // set it to current function
            // add it to IRProgram
    }

    public void printIRProgram() {
        System.out.println("program " + this.IRProg.name);
        String indentation = "    "; // 4 spaces
        for (IRFunction f : this.IRProg.functions) {
            System.out.println("\n" + f.getDeclaration() + " {");
            for (IRInstruction instr : f.instrs) {
                System.out.println(indentation + instr);
            }
            System.out.println("}");
        }
    }
}