package IR;

public class IRAssign extends IRInstruction {
    Temp target;
    IRExpression value;

    public IRAssign(Temp target, IRExpression value) {
        this.target = target;
        this.value = value;
    }

    public String toString() {
        return target.toString() + " := " + this.value + ";";
    }
}