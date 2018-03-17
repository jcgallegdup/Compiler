package IR;

import Type.*;

public class Temp {
    int id;
    Type type;

    public Temp(int id, Type type) {
        this.id = id;
        this.type = type;
    }

    public String toString() {
        return "T"+id;
    }
}