package AST;

public class TypeNode {
    String name;
    int size;

    public TypeNode (String name) {
        //System.out.println("TypeNode(): name="+name);
        this.name = name;
        size = -1;
    }

    public TypeNode (String name, int size) {
        //System.out.println("TypeNode(): name="+name);
        this.name = name;
        this.size = size;
    }

    public void accept (Visitor v) {
        v.visit(this);
        System.out.println("Visiting: " + this);
    }

    public String toString () {
        if (size > 0) {
            return this.name + " [" + this.size + "]";
        } else {
            return this.name;
        }
    }
}
