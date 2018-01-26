package AST;

import java.util.Vector;
public class FunctionBody {
    Vector<VariableDeclaration> varDecls;
    Vector<Statement> statements;

    public FunctionBody () {
        this.varDecls = new Vector<VariableDeclaration>();
        this.statements = new Vector<Statement>();
    }

    public void addVarDecl(VariableDeclaration varDecl) {
        this.varDecls.add(varDecl);
    }

    public void addStatement(Statement s) {
        this.statements.add(s);
    }

    public void accept (Visitor v) {
        v.visit(this);
        //System.out.println("Visiting: " + this);
    }
}
