package IR;

import java.util.List;
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

    // TODO implement toString()
    // - delegates to IRFunction.toString, which delegates to TempFactory.toString()
}