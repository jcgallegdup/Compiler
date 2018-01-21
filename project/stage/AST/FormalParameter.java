package AST;

public class FormalParameter {
    TypeNode type;
    Identifier id;

    public FormalParameter (TypeNode type, Identifier id) {
        this.type = type;
        this.id = id;
    }

    public void accept (Visitor v) {
        v.visit(this);
        // System.out.println("Visiting: " + this);
    }

    public String toString() {
        return this.type.toString() + " " + this.id.name;
    }
}
