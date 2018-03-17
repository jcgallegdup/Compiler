package IR;

import Type.*;

public class IRTypeHelper {
    public static String getIRTypeStr(Type t) {
        String typeStr;
        // TODO check if type is array in a non-hacky way :^(
        if (t.getElementType() != null) {
            typeStr = "A" + convert(t.getElementType().toString());
        } else {
            typeStr = convert(t.toString());
        }
        return typeStr;
    }

    private static String convert(String typeStr) {
        String typeIRStr;
        switch (typeStr) {
            case "boolean": typeIRStr = "Z"; break;
            case "int":     typeIRStr = "I"; break;
            case "float":   typeIRStr = "F"; break;
            case "string":  typeIRStr = "U"; break;
            case "char":    typeIRStr = "C"; break;
            case "void":    typeIRStr = "V"; break;
            default:        typeIRStr = null; break;
        }
        return typeIRStr;
    }
}