package AST;

import java.util.Vector;
public class FunctionBody {
    //Vector<VariableDeclaration> varDecls;
    //Vector<Statement> statements;

    public FunctionBody () {
        //this.varDecls = Vector<VariableDeclaration>();
        //this.statements = Vector<Statement>();
    }

    public void accept (Visitor v) {
        v.visit(this);
        //System.out.println("Visiting: " + this);
    }
}
