package IR;

import AST.*;
import IR.IRFuncCall;
import IR.IRInstruction;
import IR.TempManager;
import Type.*;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

public class IRGenerator {
    IRProgram prog;
    IRFunction curFunc;
    TempManager tempManager;
    Map<String, Type> funcReturnTypes;

    public IRGenerator() {
        this.prog = new IRProgram();
        // init before visiting each function
        this.curFunc = null;
        this.tempManager = null;
        this.funcReturnTypes = new HashMap<String, Type>();
    }

    public void visit(Program p) {
        // TODO add name to IRProgram
        prog.addName("classname");
        // first collect function return types for reference in FunctionCallExpression visitor
        for (Function f : p.functionList) {
            this.funcReturnTypes.put(
                f.funcDecl.id.name,
                f.funcDecl.returnType.type
            );
        }
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

    public void visit(ReturnStatement s) {
        IRReturn returnInstr;
        if (s.expr != null) {
            returnInstr = new IRReturn(s.expr.accept(this));
        } else {
            returnInstr = new IRReturn();
        }
        this.curFunc.addInstr(returnInstr);
    }

    public Temp visit(Expression e) {
        return null;
    }

    public Temp visit(FunctionCallExpression e) {
        // first, get temp vars for each function call arg
        List<Temp> args = new LinkedList<Temp>();
        for (Expression arg : e.args) {
            args.add(arg.accept(this));
        }

        // if return type is void, add function call instr w/o assigning to temp var
        IRInstruction funcCall = new IRFuncCall(e.id.name, args);
        if (this.funcReturnTypes.get(e.id.name).toString().equals("void")) {
            this.curFunc.addInstr(funcCall);
            return null;
        }

        // if return type NOT void, add temp var assign instr with value of function call
        Temp returnVal = this.tempManager.newTemp(
            this.funcReturnTypes.get(e.id.name)
        );
        this.curFunc.addVarDecl(new IRVarDecl(returnVal));
        this.curFunc.addInstr(new IRAssign(returnVal, funcCall));

        // give back the return value of function call
        return returnVal;
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