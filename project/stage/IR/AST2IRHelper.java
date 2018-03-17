package IR;

import Type.*;

public class AST2IRHelper {

    public static String getIRLiteralStr(Type t, Object val) {
        String literalIRStr;
        switch(t.toString()) {
            case "boolean": literalIRStr = val.toString().toUpperCase();
                            break;
            case "char":    literalIRStr = "\'"+val.toString()+"\'";
                            break;
            // TODO: figure out why this is not necessary apparently
            // case "string":  literalIRStr = "\""+val.toString()+"\"";
            //                 break;
            default:        literalIRStr = val.toString();
                            break;
        }
        return literalIRStr;
    }

    public static String getIRTypeStr(Type t) {
        String typeStr;
        // TODO check if type is array in a non-hacky way :^(
        if (t.getElementType() != null) {
            typeStr = "A" + convert2IR(t.getElementType().toString());
        } else {
            typeStr = convert2IR(t.toString());
        }
        return typeStr;
    }

    private static String convert2IR(String typeStr) {
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