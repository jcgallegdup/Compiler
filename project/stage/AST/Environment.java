package AST;

import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

public class Environment <K, V, T> {
    T enclosingScope;
    HashMap<K, V> symbolTable;

    public Environment(T enclosingScope) {
        // TODO consider ArrayList as alternative
        symbolTable = new HashMap<K,V>();
        this.enclosingScope = enclosingScope;
    }

    public boolean inScope(K key) {
        return this.symbolTable.containsKey(key);
    }

    // entry is added only if it doesn't overwrite an existing entry
    // returns true only if key has been added
    // no null values allowed
    public boolean add(K key, V value) throws Exception {
        if (value == null) {
            throw new Exception("Environment value may not be 'null'");
        }
        if (this.inScope(key)) {
            return false;
        } else {
            this.symbolTable.put(key, value);
            return true;
        }
    }

    // since we forbid 'null' values in our env, null signifies a miss
    public V lookup(K key) {
        return this.symbolTable.get(key);
    }

    public void dumpEnv() {
        System.out.println("Dumping env for: "+ this.enclosingScope+"\nMap:"+this.symbolTable);
    }
}