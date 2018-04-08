package IR;

import Type.*;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

import IR.AST2JasminHelper;
import IR.IRExpression;
import IR.IRExpressionInstruction;
import IR.IRFuncCall;
import IR.IRInstruction;
import IR.IRLiteralAssign;
import IR.IRNewArray;
import IR.IROperand;
import IR.IRProgram;
import IR.IRVarDecl;

public class IR2Jasmin {
    String progName;
    Map<String, Type> funcReturnTypes;
    int indentLevel;
    final int indentSize = 4;

    public IR2Jasmin() {
        this.funcReturnTypes = new HashMap<String, Type>();
        this.progName = null;
        this.indentLevel = 0;
    }

    public void visit(IRFunction func) {
        // 1. Declare function
        String funcName;
        if (func.name.equals("main")) {
            // called by other 'main' function at the end of the program
            funcName = "__main";
        } else {
            funcName = func.name;
        }
        String funcNameWithTypes = AST2JasminHelper.getFuncNameWithTypes(
            funcName,
            func.paramTypes,
            func.returnType
        );
        String decl = ".method public static " + funcNameWithTypes;
        println(decl);

        this.indentLevel++;

        // 2. declare variables and set stack limit
        println(".limit locals " + func.varDecls.size());
        for (IRVarDecl varDecl : func.varDecls) {
            varDecl.accept(this);
        }
        // TODO: change this dynamically
        println(".limit stack 20");

        // 3. generate jasmin instructions
        for (IRInstruction instr : func.instrs) {
            println("; " + instr);
            instr.accept(this);
            println("");
        }

        this.indentLevel--;
        println(".end method");
    }

    // should never actually used -- all subclasses of IRInstruction should override 'accept'
    // remove once accept method in IRInstruction is made abstract
    public void visit(IRInstruction instr) {
        return ;
    }

    public void visit(IRPrint instr) {
        String loadPrintStream = "getstatic java/lang/System/out Ljava/io/PrintStream;";
        String loadVar = AST2JasminHelper.getPrefixTypeStr(instr.var.type) + "load " + instr.var.id;
        String callPrint = "invokevirtual java/io/PrintStream/print(" + AST2JasminHelper.getTypeStr(instr.var.type) + ")V";
        println(loadPrintStream);
        println(loadVar);
        println(callPrint);
    }

    public void visit(IRPrintln instr) {
        String loadPrintStream = "getstatic java/lang/System/out Ljava/io/PrintStream;";
        String loadVar = AST2JasminHelper.getPrefixTypeStr(instr.var.type) + "load " + instr.var.id;
        String callPrintln = "invokevirtual java/io/PrintStream/println(" + AST2JasminHelper.getTypeStr(instr.var.type) + ")V";
        println(loadPrintStream);
        println(loadVar);
        println(callPrintln);
    }

    public void visit(IRExpressionInstruction instr) {
        instr.e.accept(this);
    }

    public void visit(IRAssign instr) {
        // load expression onto stack
        instr.value.accept(this);
        String assign = AST2JasminHelper.getPrefixTypeStr(instr.target.type) + "store " + instr.target.id;
        println(assign);
    }

    public void visit(IRArrayElementAssign instr) {
        // TODO: don't hardcode "a" prefix type string -- move to AST2Jasmin helper
        String loadArray = "aload " + instr.target.id;
        String loadIdx = AST2JasminHelper.getPrefixTypeStr(instr.index.type) + "load " + instr.index.id;

        println(loadArray);
        println(loadIdx);

        // load expression onto stack
        instr.value.accept(this);

        Type elemType = instr.target.type.getElementType();
        // TODO: don't hardcode "a" prefix type string -- move to AST2Jasmin helper
        String storeArray = AST2JasminHelper.getArrayStorePrefixTypeStr(elemType) + "astore";
        println(storeArray);
    }

    public void visit(IRNewArray instr) {
        Type elemType = instr.type.getElementType();
        String loadSize = "ldc " + instr.size;
        String createArray = AST2JasminHelper.getNewArrayPrefixStr(elemType) + "newarray " + AST2JasminHelper.getNewArrayElementTypeStr(elemType);
        println(loadSize);
        println(createArray);
    }

    public void visit(IRReturn instr) {
        String typePrefix;
        // if the function's return type is void, the return statement has no variable
        if (instr.var == null) {
            typePrefix = "";
        } else {
            typePrefix = AST2JasminHelper.getPrefixTypeStr(instr.var.type);
            // load any function arguments onto the stack
            println(typePrefix + "load " + instr.var.id);
        }
        println(typePrefix + "return");
    }

    public void visit(IRLiteralAssign instr) {
        String load = "ldc " + AST2JasminHelper.getLiteral(instr.target.type, instr.value);
        String store = AST2JasminHelper.getPrefixTypeStr(instr.target.type) + "store " + instr.target.id;
        println(load);
        println(store);
    }

    public void visit(IRVarDecl varDecl) {
        String declStr = ".var " + varDecl.var.id + " is " + varDecl.var + " " + AST2JasminHelper.getTypeStr(varDecl.type);
        println(declStr);
    }

    // should never actually used -- all subclasses of IRInstruction should override
    public void visit(IRExpression e) {
        return ;
    }

    public void visit(IROperand e) {
        String typePrefix = AST2JasminHelper.getPrefixTypeStr(e.operand.type);
        println(typePrefix + "load " + e.operand.id);
    }

    public void visit(IRFuncCall e) {
        // ** (NOTE #1) **
        // TODO: refactor classes so that prog name and paramTypes are accessible through IRFuncCall
        //  probably by a chain of references: IRFuncCall -> IRFunction -> IRProg
        // ==> likely means that AST.FunctionCallExpression needs to hold a reference to IRFunction
        List<Type> paramTypes = new LinkedList<Type>();
        for (Temp arg : e.args) {
            paramTypes.add(arg.type);
            println(AST2JasminHelper.getPrefixTypeStr(arg.type) + "load " + arg.id);
        }
        String funcNameWithTypes = AST2JasminHelper.getFuncNameWithTypes(
            e.funcName,
            paramTypes,
            this.funcReturnTypes.get(e.funcName)
        );

        String funcInvocation = "invokestatic " + this.progName + "/" + funcNameWithTypes;
        println(funcInvocation);
    }

    public void visit(IRProgram prog) {
        // TODO: remove this once (NOTE #1) is resolved
        this.progName = prog.name;

        // first collect function return types for reference in FunctionCallExpression visitor
        for (IRFunction func : prog.functions) {
            this.funcReturnTypes.put(
                func.name,
                func.returnType
            );
        }

        // TODO: determine if .source is necessary
        String progInfo = ""
            // + ".source " + prog.name + ".ul\n"
            + ".class " + prog.name + "\n"
            + ".super java/lang/Object\n"
        ;
        println(progInfo);

        for (IRFunction func : prog.functions) {
            func.accept(this);
            System.out.println();
        }

        // TODO: validate this Jasmin code (I just copied it from example prog)
        String progInit = ""
            +".method public static main([Ljava/lang/String;)V\n"
            +"    ; set limits used by this method\n"
            +"    .limit locals 1\n"
            +"    .limit stack 4\n"
            +"    invokestatic "+ prog.name +"/__main()V\n"
            +"    return\n"
            +".end method\n"

            + "; standard initializer\n"
            + ".method public <init>()V\n"
            + "    aload_0\n"
            + "    invokenonvirtual java/lang/Object/<init>()V\n"
            + "    return\n"
            + ".end method\n"
        ;
        println(progInit);
    }

    // encapsulates indentation logic
    private void println(String line) {
        // https://stackoverflow.com/questions/1235179/simple-way-to-repeat-a-string-in-java/4903603#4903603
        String indentation = new String(new char[indentSize * this.indentLevel]).replace("\0", " ");
        System.out.println(indentation + line);
    }
}