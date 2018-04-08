package IR;

import Type.*;

public class AST2JasminHelper {
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