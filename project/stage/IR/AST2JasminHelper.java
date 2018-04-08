package IR;

import Type.*;

public class AST2JasminHelper {

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

    public static String getStorePrefixTypeStr(Type t) {
        String typePrefix;
        switch (t.toString()) {
            case "boolean": typePrefix = "i"; break;
            case "int":     typePrefix = "i"; break;
            case "char":    typePrefix = "i"; break;
            case "float":   typePrefix = "f"; break;
            case "string":  typePrefix = "a"; break;
            default:        typePrefix = null; break;
        }
        return typePrefix;
    }

    public static String getJasminTypeStr(Type t) {
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