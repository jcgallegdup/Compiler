package AST;

import Type.*;

import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;

public class OperandTypeRules {

    // if spec'd operation is valid, the resulting type is returned
    // o.w. None is returned
    public static Type getTypeOfResult(String op, Type left, Type right) {
        if (left.toString().equals("void") || right.toString().equals("void")) {
            return null;
        }

        Type result = null;
        if (op.equals("==") || op.equals("<")) {
            result = (left.equals(right)? new BooleanType() : null);

        } else if (op.equals("+")) {
            result = add(left, right);

        } else if (op.equals("-")) {
            result = sub(left, right);

        } else if (op.equals("*")) {
            result = mult(left, right);

        }
        return result;
    }

    private static Type add(Type left, Type right) {
        if (left.toString().equals("boolean") || right.toString().equals("boolean"))  {
            return null;
        } else {
            return (left.equals(right)? left : null);
        }
    }

    private static Type sub(Type left, Type right) {
        if (left.toString().equals("boolean") || right.toString().equals("boolean"))  {
            return null;
        } else if (left.toString().equals("string") || right.toString().equals("string"))  {
            return null;
        } else {
            return (left.equals(right)? left : null);
        }
    }

    private static Type mult(Type left, Type right) {
        if (left.toString().equals("boolean") || right.toString().equals("boolean"))  {
            return null;
        } else if (left.toString().equals("string") || right.toString().equals("string"))  {
            return null;
        } else if(left.toString().equals("char") || right.toString().equals("char")) {
            return null;
        } else {
            return (left.equals(right)? left : null);
        }
    }
}