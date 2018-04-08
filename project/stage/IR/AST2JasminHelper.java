package IR;

import java.util.List;

import Type.*;

public class AST2JasminHelper {

    // used for both function declaration and invocation
    public static String getFuncNameWithTypes(String funcName, List<Type> paramTypes, Type returnType) {
        String typeStr = "(";
        String separator = "";
        for (Type t : paramTypes) {
            typeStr += separator + AST2JasminHelper.getTypeStr(t);
            separator = " ";
        }
        typeStr += ")";
        return funcName + typeStr + getTypeStr(returnType);
    }

    public static Object getLiteral(Type type, Object literal) {
        String typeStr = type.toString();
        Object val;
        if (typeStr.equals("boolean")) {
            // map True=>1 False=>0
            val = (Boolean) literal? 1 : 0;
        } else if (typeStr.equals("char")) {
            // encode in ASCII e.g. 'c' => 99
            val = (int) ((char) literal);
        } else {
            val = literal;
        }
        return val;
    }

    public static String getPrefixTypeStr(Type t) {
        // arrays are a special case
        if (t.getElementType() != null) {
            return "a";
        }
        String typePrefix;
        switch (t.toString()) {
            case "boolean": typePrefix = "i"; break;
            case "int":     typePrefix = "i"; break;
            case "char":    typePrefix = "i"; break;
            case "float":   typePrefix = "f"; break;
            case "string":  typePrefix = "a"; break;
            case "void":    typePrefix = ""; break;
            default:        typePrefix = null; break;
        }
        return typePrefix;
    }

    public static String getArrayStorePrefixTypeStr(Type t) {
        String typePrefix;
        switch (t.toString()) {
            case "boolean": typePrefix = "b"; break;
            case "int":     typePrefix = "i"; break;
            case "char":    typePrefix = "c"; break;
            case "float":   typePrefix = "f"; break;
            case "string":  typePrefix = "a"; break;
            default:        typePrefix = null; break;
        }
        return typePrefix;
    }

    public static String getNewArrayElementTypeStr(Type elemType) {
        String elemTypeStr;
        switch(elemType.toString()) {
            case "boolean": elemTypeStr = "boolean"; break;
            case "int":     elemTypeStr = "int";     break;
            case "char":    elemTypeStr = "char";    break;
            case "float":   elemTypeStr = "float";   break;
            case "string":  elemTypeStr = "java/lang/String"; break;
            default:        elemTypeStr = null;      break;
        }
        return elemTypeStr;
    }

    public static String getNewArrayPrefixStr(Type elemType) {
        return (elemType.toString().equals("string")? "a" : "");
    }

    public static String getTypeStr(Type t) {
        String typeStr;
        // TODO check if type is array in a non-hacky way :^(
        if (t.getElementType() != null) {
            typeStr = "[" + convert2Jasmin(t.getElementType().toString());
        } else {
            typeStr = convert2Jasmin(t.toString());
        }
        return typeStr;
    }

    private static String convert2Jasmin(String typeStr) {
        String typeIRStr;
        switch (typeStr) {
            case "boolean": typeIRStr = "Z"; break;
            case "int":     typeIRStr = "I"; break;
            case "float":   typeIRStr = "F"; break;
            case "char":    typeIRStr = "C"; break;
            case "void":    typeIRStr = "V"; break;
            case "string":  typeIRStr = "Ljava/lang/String;"; break;
            default:        typeIRStr = null; break;
        }
        return typeIRStr;
    }
}