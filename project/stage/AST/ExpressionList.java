package AST;

import java.util.Vector;

public class ExpressionList {
    Vector<Expression> expressions;

    public ExpressionList () {
        this.expressions = new Vector<Expression>();
    }

    public void addElement(Expression expr) {
        expressions.add(expr);
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
