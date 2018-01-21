package AST;

public class Identifier {
    String name;

    public Identifier (String name) {
        this.name = name;
    }

    public void accept (Visitor v) {
        v.visit(this);
        System.out.println("Visiting: " + this);
    }

    public String toString() {
        return name;
    }
}
