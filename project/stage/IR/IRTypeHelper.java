package IR;

import Type.*;

public class IRTypeHelper {
    public static String getIRTypeStr(Type t) {
        String typeStr;
        switch (t.toString()) {
            case "boolean": typeStr = "Z"; break;
            case "int":     typeStr = "I"; break;
            case "float":   typeStr = "F"; break;
            case "string":  typeStr = "S"; break;
            case "char":    typeStr = "C"; break;
            case "void":    typeStr = "V"; break;
            default:        typeStr = null; break;
        }
        // TODO check if type is array in a non-hacky way :^(
        if (typeStr == null && t.getElementType() != null) {
            typeStr = "A";
        }
        return typeStr;
    }
}