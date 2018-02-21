package AST;

import java.util.Vector;
import java.util.Iterator;

public class FormalParameterList implements Iterable<FormalParameter> {
    Vector<FormalParameter> params;

    public FormalParameterList () {
        this.params = new Vector<FormalParameter>();
    }

    public void addElement(FormalParameter param) {
        params.add(param);
    }

    public void accept (Visitor v) {
        v.visit(this);
        // System.out.println("Visiting: " + this);
    }

    public String toString () {
        String paramsString = "(";
        String delimiter = "";
        for (FormalParameter param : this.params) {
            paramsString += delimiter + param.toString();
            delimiter = ", ";
        }
        paramsString += ")";
        return paramsString;
    }

    public int size() {
        return params.size();
    }

    @Override
    public Iterator<FormalParameter> iterator() {
        return params.iterator();
    }
}
