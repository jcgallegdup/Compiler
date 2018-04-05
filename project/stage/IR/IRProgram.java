package IR;

import java.util.List;

import IR.IR2Jasmin;

import java.util.LinkedList;

public class IRProgram {
    String name;
    List<IRFunction> functions;

    public IRProgram() {
        this.functions = new LinkedList<IRFunction>();
    }

    public void addName(String name) {
        this.name = name;
    }

    public void addFunction(IRFunction f) {
        this.functions.add(f);
    }

    public void accept(IR2Jasmin v) {
        v.visit(this);
    }

    // TODO implement toString()
    // - delegates to IRFunction.toString, which delegates to TempFactory.toString()
}