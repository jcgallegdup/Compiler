package IR;

import java.util.List;
import Type.*;

public class IRFuncCall extends IRExpression {
    // TODO: ensure we only need the called function's name
    String funcName;
    List<Temp> args;

    public IRFuncCall(String funcName, List<Temp> args) {
        this.funcName = funcName;
        this.args = args;
    }

    public void accept(IR2Jasmin v) {
        v.visit(this);
    }

    // e.g. 'CALL foo (I F)' if calling 'foo(int arg1, float arg2)'
    public String toString() {
        String argsStr = "(";
        String separator = "";
        for (Temp arg : args) {
            argsStr += separator + arg;
            separator = " ";
        }
        argsStr += ")";
        return "CALL " + this.funcName + " " + argsStr;
    }
}