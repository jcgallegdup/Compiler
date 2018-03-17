package IR;

import java.util.List;
import Type.*;

// TODO: consider extending a new IRExpr class instead
public class IRFuncCall extends IRInstruction {
    // TODO: ensure we only need the called function's name
    String funcName;
    List<Temp> args;

    public IRFuncCall(String funcName, List<Temp> args) {
        this.funcName = funcName;
        this.args = args;
    }

    // e.g. 'CALL foo (I F);' if calling 'foo(int arg1, float arg2)'
    public String toString() {
        String argsStr = "(";
        String separator = "";
        for (Temp arg : args) {
            argsStr += separator + arg;
            separator = " ";
        }
        argsStr += ")";
        return "CALL " + this.funcName + " " + argsStr + ";";
    }
}