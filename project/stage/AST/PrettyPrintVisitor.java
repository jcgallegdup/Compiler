package AST;

import Type.*;

public class PrettyPrintVisitor implements Visitor {
    private final int INDENTSIZE = 4;
    int indentLevel;

    public PrettyPrintVisitor() {
        this.indentLevel = 0;
    }

    public void visit (Program prog) {
        for (Function func : prog.functionList) {
            func.accept(this);
        }
    }

    public void visit (Function func) {
        func.funcDecl.accept(this);
        func.funcBody.accept(this);
    }

    public void visit (FunctionDecl fd) {
        println (fd.toString());
    }

    public void visit (FunctionBody fb) {
        println ("{");
        indentLevel++;
        for (VariableDeclaration varDecl : fb.varDecls) {
            varDecl.accept(this);
        }
        for (Statement s : fb.statements) {
            s.accept(this);
        }
        indentLevel--;
        println ("}");
    }

    public void visit (VariableDeclaration varDecl) {
        println (varDecl.toString());
    }

    public void visit(PrintStatement s) {
        println(s.toString());
    }

    public void visit(PrintlnStatement s) {
        println(s.toString());
    }

    public void visit(ReturnStatement s) {
        println(s.toString());
    }

    public void visit(Expression e) {
        println(e.toString());
    }

    public void visit (FormalParameterList params) { }

    public void visit (FormalParameter param) { }

    public void visit (TypeNode typeNode) { }

    public void visit (Type t) { }

    public void visit (Identifier id) { }

    // change arg to ASTNode, making toString() implicit
    private void println (String line) {
        int numSpaces = this.INDENTSIZE * this.indentLevel;
        // https://stackoverflow.com/questions/1235179/simple-way-to-repeat-a-string-in-java/4903603#4903603
        String indentation = new String(new char[numSpaces]).replace("\0", " ");
        System.out.println(indentation + line);

    }
}
