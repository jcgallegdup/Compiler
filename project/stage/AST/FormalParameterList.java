package AST;

import java.util.Vector;

public class FormalParameterList {
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
}
