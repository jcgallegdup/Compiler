package IR;

import IR.IRProgram;

public class IR2Jasmin {
    public void visit(IRProgram prog) {
        // TODO: determine if .source is necessary
        String progInfo = ""
            // + ".source " + prog.name + ".ul\n"
            + ".class " + prog.name + "\n"
            + ".super java/lang/Object\n"
        ;
        System.out.println(progInfo);

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