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
            this.curFunc.addVarDecl(new IRVarDecl(var));
        }
    }

    public void visit(FunctionBody body) {
        for (VariableDeclaration varDecl : body.varDecls) {
            varDecl.accept(this);
        }
        for (Statement s : body.statements) {
            s.accept(this);
        }
    }

    public void visit(VariableDeclaration varDecl) {
        Temp var = this.tempManager.newTemp(varDecl.type.type);
        this.curFunc.addVarDecl(new IRVarDecl(var));
    }

    public void visit(Statement s) { }

    public void visit(PrintlnStatement s) {
        Temp var = s.expr.accept(this);
        this.curFunc.addInstr(
            new IRPrintln(var)
        );
    }

    public void visit(PrintStatement s) {
        Temp var = s.expr.accept(this);
        this.curFunc.addInstr(
            new IRPrint(var)
        );
    }

    public Temp visit(Expression e) {
        return null;
    }

    public Temp visit(LiteralExpression e) {
        // we need a new temp var to hold the literal, which involves declaring it
        Temp var = this.tempManager.newTemp(e.type);
        this.curFunc.addVarDecl(
            new IRVarDecl(var)
        );
        this.curFunc.addInstr(
            new IRLiteralAssign(var, e.val)
        );
        return var;
    }

    public void printIRProgram() {
        System.out.println("PROG " + this.prog.name);
        String indentation = "    "; // 4 spaces
        for (IRFunction f : this.prog.functions) {
            System.out.println("\n" + f.getDeclaration() + " {");
            for (IRInstruction instr : f.getAllInstrs()) {
                System.out.println(indentation + instr);
            }
            System.out.println("}");
        }
    }
}