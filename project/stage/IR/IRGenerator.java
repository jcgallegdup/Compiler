package IR;

import AST.*;
import IR.IRInstruction;
import Type.*;

import java.util.List;
import java.util.LinkedList;

public class IRGenerator {
    IRProgram prog;
    IRFunction curFunc;

    public IRGenerator() {
        this.prog = new IRProgram();
        this.curFunc = null;
    }

    public void visit(Program p) {
        // TODO add name to IRProgram
        prog.addName("TODO");
        for (Function f : p.functionList) {
            f.accept(this);
        }
    }

    public void visit(Function f) {
        this.curFunc = new IRFunction(
            f.funcDecl.id.name,
            f.funcDecl.returnType.type,
            f.funcDecl.params.getTypes()
        );

        this.prog.addFunction(this.curFunc);
    }

    public void printIRProgram() {
        System.out.println("program " + this.prog.name);
        String indentation = "    "; // 4 spaces
        for (IRFunction f : this.prog.functions) {
            System.out.println("\n" + f.getDeclaration() + " {");
            for (IRInstruction instr : f.instrs) {
                System.out.println(indentation + instr);
            }
            System.out.println("}");
        }
    }
}