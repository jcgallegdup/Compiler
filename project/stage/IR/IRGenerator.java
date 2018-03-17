package IR;

import AST.*;
import IR.IRInstruction;
import IR.TempManager;
import Type.*;

import java.util.List;
import java.util.LinkedList;

public class IRGenerator {
    IRProgram prog;
    IRFunction curFunc;
    TempManager tempManager;

    public IRGenerator() {
        this.prog = new IRProgram();
        // init before visiting each function
        this.curFunc = null;
        this.tempManager = null;
    }

    public void visit(Program p) {
        // TODO add name to IRProgram
        prog.addName("classname");
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
        this.tempManager = new TempManager();

        // create instrs for param var declarations
        f.funcDecl.params.accept(this);
        // create instrs for function var declarations and statements
        f.funcBody.accept(this);

        this.prog.addFunction(this.curFunc);
    }

    public void visit(FormalParameterList params) {
        for (FormalParameter param : params) {
            Temp var = this.tempManager.newTemp(param.type.type);
            IRInstruction instr = new IRVarDecl(var);
            this.curFunc.addInstr(instr);
        }
    }

    public void visit(FunctionBody body) {
        for (VariableDeclaration varDecl : body.varDecls) {
            varDecl.accept(this);
        }
    }

    public void visit(VariableDeclaration varDecl) {
        Temp var = this.tempManager.newTemp(varDecl.type.type);
        IRInstruction instr = new IRVarDecl(var);
        this.curFunc.addInstr(instr);
    }

    public void printIRProgram() {
        System.out.println("PROG " + this.prog.name);
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