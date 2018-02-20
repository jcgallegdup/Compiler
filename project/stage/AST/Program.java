package AST;

import java.util.Vector;
public class Program {
    public Vector<Function> functionList;

    public Program () {
        functionList = new Vector<Function>();
    }

    public void addElement (Function f) {
        functionList.addElement(f);
    }

    public Function elementAt (int index) {
        return (Function) functionList.elementAt(index);
    }

    public int size () {
        return functionList.size();
    }

    public void accept (TypeCheckVisitor v) throws SemanticException {
        v.visit(this);
    }

    public void accept (Visitor v) {
        v.visit(this);
        //System.out.println("Visiting :" + this);
    }
}
