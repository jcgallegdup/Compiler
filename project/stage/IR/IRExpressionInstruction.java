package IR;

// necessary for function calls with void return types
public class IRExpressionInstruction extends IRInstruction {
    IRExpression e;

    public IRExpressionInstruction(IRExpression e) {
        this.e = e;
    }

    public String toString() {
        return this.e.toString() + ";";
    }
}