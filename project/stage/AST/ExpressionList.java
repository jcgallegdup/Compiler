package AST;

import Type.Type;

import java.util.Vector;

public class ExpressionList {
    Vector<Expression> expressions;

    public ExpressionList () {
        this.expressions = new Vector<Expression>();
    }

    public void addElement(Expression expr) {
        expressions.add(expr);
    }

    public int size() {
        return expressions.size();
    }

    public Expression get(int i) {
        return expressions.get(i);
    }

    public String toString () {
        String expressionsStr = "";
        String delimiter = "";
        for (Expression expr : this.expressions) {
            expressionsStr += delimiter + expr.toString();
            delimiter = ", ";
        }
        return expressionsStr;
    }
}
