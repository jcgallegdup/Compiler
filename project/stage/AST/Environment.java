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
    public boolean add(K key, V value) {
        if (this.inScope(key)) {
            return false;
        } else {
            this.symbolTable.put(key, value);
            return true;
        }
    }

    public V lookup(K key) {
        return this.symbolTable.get(key);
    }

    public void dumpEnv() {
        System.out.println(this.enclosingScope+"\nMap:"+this.symbolTable);
    }
}