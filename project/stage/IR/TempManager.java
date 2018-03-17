package IR;

import AST.Expression;
import Type.*;

public class TempManager {
    // TODO enforce max temp vars
    int total;

    // TODO implement usage table, allowing temps reuse
    // TempEntry
        //  int number
        //  boolean inUse
        //  Type type
        //  String name
        //  TempClass class
    // TODO
    // enum TempClass{
    //     UNKNOWN, PARAMETER, LOCAL, TEMP
    // }

    public TempManager() {
        this.total = 0;
    }

    public Temp newTemp(Type t) {
        // declare new Temp instance
            // add to global list of Temps
            // return it
        return new Temp(this.total++, t);
    }

    // TODO: indicate that temp variable is not longer used
    // public void free(Temp var) { }
}