package AST;

import Type.Type;

import java.util.Vector;
import java.util.Iterator;

public class ExpressionList implements Iterable<Expression> {
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

    @Override
    public Iterator<Expression> iterator() {
        return this.expressions.iterator();
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
