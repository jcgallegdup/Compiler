package IR;

public class IRArrayAccess extends IRExpression {
    Temp array, index;

    public IRArrayAccess(Temp array, Temp index) {
        this.array = array;
        this.index = index;
    }

    public void accept(IR2Jasmin v) {
        v.visit(this);
    }

    public String toString() {
        return this.array.toString() + "[" + this.index.toString() + "]";
    }
}