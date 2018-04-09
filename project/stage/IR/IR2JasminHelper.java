package IR;

import java.util.List;
import java.util.LinkedList;

import IR.IRBinaryOp;
import Type.*;

public class IR2JasminHelper {
    public static List<String> getLessThanCompStr(Type type, String ifTrueLabel) {
        List<String> comparisonInstrs = new LinkedList<String>();
        switch (type.toString()) {
            case "int":
                comparisonInstrs.add("isub");
                comparisonInstrs.add("iflt " + ifTrueLabel);
                break;
            case "char":
                comparisonInstrs.add("isub");
                comparisonInstrs.add("iflt " + ifTrueLabel);
                break;

            case "float":
                comparisonInstrs.add("fcmpg");
                comparisonInstrs.add("iflt " + ifTrueLabel);
                break;

            // case "string":      opStr = "invokevirtual java/lang/String/compareTo(Ljava/lang/String;)I"; break;
        }
        return comparisonInstrs;
    }

    public static List<String> getEqualsCompStr(Type type, String ifTrueLabel) {
        List<String> comparisonInstrs = new LinkedList<String>();
        switch (type.toString()) {
            case "int":
                comparisonInstrs.add("isub");
                comparisonInstrs.add("ifeq " + ifTrueLabel);
                break;
            case "char":
                comparisonInstrs.add("isub");
                comparisonInstrs.add("ifeq " + ifTrueLabel);
                break;
            case "boolean":
                comparisonInstrs.add("isub");
                comparisonInstrs.add("ifeq " + ifTrueLabel);
                break;

            case "float":
                comparisonInstrs.add("fcmpg");
                comparisonInstrs.add("ifeq " + ifTrueLabel);
                break;

            // case "string":      opStr = "invokevirtual java/lang/String/compareTo(Ljava/lang/String;)I"; break;
        }
        return comparisonInstrs;
    }
}