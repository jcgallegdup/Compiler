package IR;

public class IRArrayAccess extends IRExpression {
    Temp array, index;

    public IRArrayAccess(Temp array, Temp index) {
        this.array = array;
        this.index = index;
    }

    public String toString() {
        return this.array.toString() + "[" + this.index.toString() + "]";
    }
}