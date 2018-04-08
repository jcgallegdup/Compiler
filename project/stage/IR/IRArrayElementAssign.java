package IR;

public class IRArrayElementAssign extends IRInstruction {
    Temp target, index;
    IRExpression value;

    public IRArrayElementAssign(Temp target, Temp index, IRExpression value) {
        this.target = target;
        this.index = index;
        this.value = value;
    }

    public void accept(IR2Jasmin v) {
        v.visit(this);
    }

    public String toString() {
        return target.toString() + "[" + index + "] := " + this.value + ";";
    }
}