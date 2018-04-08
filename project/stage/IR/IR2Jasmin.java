package IR;

import Type.*;

import java.util.List;

import IR.AST2JasminHelper;

public class IR2Jasmin {
    final int indentSize = 4;
    // https://stackoverflow.com/questions/1235179/simple-way-to-repeat-a-string-in-java/4903603#4903603
    final String indentation = new String(new char[indentSize]).replace("\0", " ");

    public void visit(IRFunction func) {
        // 1. Declare function
        String funcName;
        if (func.name.equals("main")) {
            // called by other 'main' function at the end of the program
            funcName = "__main";
        } else {
            funcName = func.name;
        }
        String paramsTypeStr = getJasminParamTypes(func.paramTypes);
        String returnTypeStr = AST2JasminHelper.getJasminTypeStr(func.returnType);
        String decl = ".method public static " + funcName + paramsTypeStr + returnTypeStr;
        System.out.println(decl);

        // 2. declare variables and set stack limit
        String varDecls = indentation + ".limit locals " + func.varDecls.size();
        System.out.println(indentation + varDecls);

        System.out.println(".end method");
    }

    private String getJasminParamTypes(List<Type> paramTypes) {
        String typeStr = "(";
        String separator = "";
        for (Type t : paramTypes) {
            typeStr += separator + AST2JasminHelper.getJasminTypeStr(t);
            separator = " ";
        }
        typeStr += ")";
        return typeStr;
    }

    public void visit(IRProgram prog) {
        // TODO: determine if .source is necessary
        String progInfo = ""
            // + ".source " + prog.name + ".ul\n"
            + ".class " + prog.name + "\n"
            + ".super java/lang/Object\n"
        ;
        System.out.println(progInfo);

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
            +"    invokestatic classname/__main()V\n"
            +"    return\n"
            +".end method\n"

            + "; standard initializer\n"
            + ".method public <init>()V\n"
            + "    aload_0\n"
            + "    invokenonvirtual java/lang/Object/<init>()V\n"
            + "    return\n"
            + ".end method\n"
        ;
        System.out.println(progInit);
    }
}