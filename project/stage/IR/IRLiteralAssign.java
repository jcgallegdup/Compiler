package IR;

public class IRLiteralAssign<T> extends IRInstruction {
    Temp target;
    T value;

    public IRLiteralAssign(Temp target, T literal) {
        this.target = target;
        this.value = literal;
    }

    public void accept(IR2Jasmin v) {
        v.visit(this);
    }

    public String toString() {
        String valStr = AST2IRHelper.getIRLiteralStr(this.target.type, this.value);
        return target.toString() + " := " + valStr + ";";
    }
}