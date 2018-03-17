package IR;

public class IRAssign extends IRInstruction {
    Temp target;
    // TODO: in the future, value will be of type IRExpr
    IRInstruction value;

    public IRAssign(Temp target, IRInstruction value) {
        this.target = target;
        this.value = value;
    }

    public String toString() {
        // NOTE: left out ending semicolon, assuming it is supplied by this.value
        return target.toString() + " := " + this.value;
    }
}