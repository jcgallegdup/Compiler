package AST;

public class FormalParameter {
    public TypeNode type;
    public Identifier id;

    public FormalParameter (TypeNode type, Identifier id) {
        this.type = type;
        this.id = id;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public String toString() {
        return this.type.toString() + " " + this.id.name;
    }
}
