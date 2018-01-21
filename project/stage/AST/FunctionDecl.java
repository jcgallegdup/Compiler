package AST;

import Type.*;
import java.util.Vector;
public class FunctionDecl {
    TypeNode returnType;
    Identifier id;
    FormalParameterList params;

    public FunctionDecl (TypeNode returnType, Identifier id, FormalParameterList params) {
        this.returnType = returnType;
        this.id = id;
        this.params = params;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public String toString () {
        return returnType.toString() + " " + id.name + params.toString();
    }
}
