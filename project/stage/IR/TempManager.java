package IR;

import AST.Expression;
import Type.*;
import java.util.Map;
import java.util.HashMap;

public class TempManager {
    Map<String, Temp> tempVarTable;
    // TODO enforce max temp vars
    int total;

    // TODO implement TempEntry object, allowing temps reuse
    // public class TempEntry {
    //     Temp var;
    //     TempClass class
    //     boolean inUse
    //     public TempEntry() { }
    // }

    // TODO
    // enum TempClass{
    //     UNKNOWN, PARAMETER, LOCAL, TEMP
    // }

    public TempManager() {
        this.tempVarTable = new HashMap<String, Temp>();
        this.total = 0;
    }

    // retrieves temp object given the string name, which corresponds to variable id
    // returns: Temp object, or null if absent in table
    public Temp lookup(String name) {
        return this.tempVarTable.get(name);
    }

    public Temp newTemp(Type t, String name) {
        Temp var = new Temp(this.total++, t);
        this.tempVarTable.put(name, var);
        return var;
    }

    public Temp newTemp(Type t) {
        Temp var = new Temp(this.total++, t);
        this.tempVarTable.put(
            createTableKeyStr(var.id),
            var
        );
        return var;
    }

    private String createTableKeyStr(int id) {
        return "TEMP"+id;
    }

    // TODO: indicate that temp variable is not longer used
    // public void free(Temp var) { }
}