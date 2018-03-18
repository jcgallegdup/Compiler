package IR;

public class IROperand extends IRExpression {
    Temp operand;

    public IROperand(Temp operand) {
        this.operand = operand;
    }

    public String toString() {
        return this.operand.toString();
    }
}