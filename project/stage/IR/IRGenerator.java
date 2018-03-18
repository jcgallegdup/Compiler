package IR;

import AST.*;
import IR.IRAssign;
import IR.IRExpression;
import IR.IRFuncCall;
import IR.IRInstruction;
import IR.IRLabel;
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
    int labelCount;

    public IRGenerator() {
        this.prog = new IRProgram();
        // init before visiting each function
        this.curFunc = null;
        this.tempManager = null;
        this.funcReturnTypes = new HashMap<String, Type>();
        this.labelCount = -1;
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
        this.labelCount = 0;

        // create instrs for param var declarations
        f.funcDecl.params.accept(this);
        // create instrs for function var declarations and statements
        f.funcBody.accept(this);

        this.prog.addFunction(this.curFunc);
    }

    public void visit(FormalParameterList params) {
        for (FormalParameter param : params) {
            Temp var = this.tempManager.newTemp(param.type.type, param.id.name);
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
        Temp var = this.tempManager.newTemp(varDecl.type.type, varDecl.id.name);
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

    public void visit(ExpressionStatement s) {
        s.expr.accept(this);
    }

    public void visit(IfElseStatement s) {
        // 1. create+add conditional jump instr to new label for if instructions
        Temp cond = s.cond.accept(this);
        IRLabel ifLabel = new IRLabel(this.labelCount++);
        IRInstruction jumpToIfBlock = new IRConditionalJump(ifLabel, cond);
        this.curFunc.addInstr(jumpToIfBlock);

        // 2. visit else StatementBlock (which will involve adding a bunch of instructions)
        if (s.elseBlock != null) {
            s.elseBlock.accept(this);
        }

        // 3. create+add UNconditional jump instr to new label, skipping past upcoming if block
        IRLabel endLabel = new IRLabel(this.labelCount++);
        IRInstruction jumpPastIfBlock = new IRJump(endLabel);
        this.curFunc.addInstr(jumpPastIfBlock);

        // 4. add instruction with the if label from step #2
        this.curFunc.addInstr(ifLabel);
        // 5. visit if StatementBlock (like #3)
        s.ifBlock.accept(this);

        // 6. add label instr from step #4
        this.curFunc.addInstr(endLabel);
    }

    public void visit(WhileStatement s) {
        // 1. create+add label for start of loop and add it as instr
        IRLabel startLoop = new IRLabel(this.labelCount++);
        this.curFunc.addInstr(startLoop);

        // 2. negate condition to use for jumping out of loop
        Temp cond = s.cond.accept(this);
        Temp negatedCond = this.tempManager.newTemp(cond.type);
        this.curFunc.addVarDecl(new IRVarDecl(negatedCond));

        IRExpression negationOp = new IRUnaryOp(cond, IRUnaryOp.Ops.NEGATE);
        IRInstruction assign = new IRAssign(negatedCond, negationOp);
        this.curFunc.addInstr(assign);

        // 3. create+add conditional goto to exit loop using negated condition
        // --> create exit label instruction but don't add yet (until #6)
        IRLabel exitLabel = new IRLabel(this.labelCount++);
        IRInstruction exitLoop = new IRConditionalJump(exitLabel, negatedCond);
        this.curFunc.addInstr(exitLoop);

        // 4. visit loop body
        s.loopBody.accept(this);

        // 5. create+add UNconditional goto to top of loop
        IRInstruction newIteration = new IRJump(startLoop);
        this.curFunc.addInstr(newIteration);

        // 6. add label instruction from #3
        this.curFunc.addInstr(exitLabel);
    }

    public void visit(ScalarAssignmentStatement s) {
        IRExpression expr = new IROperand(s.expr.accept(this));
        Temp var = this.tempManager.lookup(s.id.name);
        IRInstruction assignment = new IRAssign(var, expr);
        this.curFunc.addInstr(assignment);
    }

    public void visit(StatementBlock block) {
        for (Statement s : block.statements) {
            s.accept(this);
        }
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
        IRExpression funcCall = new IRFuncCall(e.id.name, args);
        if (this.funcReturnTypes.get(e.id.name).toString().equals("void")) {
            IRInstruction instr = new IRExpressionInstruction(funcCall);
            this.curFunc.addInstr(instr);
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

    public Temp visit(IdentifierExpression e) {
        // TODO: check for null (even though we've already semantic-checked for IDs)
        return this.tempManager.lookup(e.id.name);
    }

    public Temp visit(BinaryExpression e) {
        // get temp vars for result and both operands
        Temp left = e.left.accept(this);
        Temp right = e.right.accept(this);
        String opStr = e.getOperator();
        Type resultType = OperandTypeRules.getTypeOfResult(opStr, left.type, right.type);
        Temp result = this.tempManager.newTemp(resultType);
        this.curFunc.addVarDecl(new IRVarDecl(result));

        // perform bin op and store result in temp var
        // NOTE: both operands are assumed to be of the same type
        // --> subtyping would require explicit casting (unary op) in a separate instruction
        IRExpression op = new IRBinaryOp(left, right, left.type, e.getOperator());
        IRInstruction assign = new IRAssign(result, op);
        this.curFunc.addInstr(assign);
        return result;
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