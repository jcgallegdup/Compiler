package AST;

import java.util.Vector;

import IR.IRGenerator;

public class FunctionBody {
    public Vector<VariableDeclaration> varDecls;
    public Vector<Statement> statements;

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

    public void accept (IRGenerator v) {
        v.visit(this);
    }

    public void accept (TypeCheckVisitor v) throws SemanticException {
        v.visit(this);
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public String toString() {
        String body = "";
        for (VariableDeclaration varDecl : this.varDecls) {
            body += varDecl.toString() + "\n";
        }
        if (this.varDecls.size() > 0) body += "\n";
        for (Statement s : statements) {
            body += s.toString() + "\n";
        }
        return body;
    }
}
