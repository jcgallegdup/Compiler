package AST;

import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

public class Environment <K, V> {
    List<HashMap<K, V>> env;
    int curScope;

    public Environment() {
        // TODO consider ArrayList as alternative
        env = new LinkedList<HashMap<K,V>>();
        this.curScope = -1;
    }

    public void beginScope() {
        env.add(new HashMap<K,V>());
        this.curScope++;
    }

    public void endScope() {
        // TODO: remove HashMap @idx=this.curScope from env list
        // TODO: verify this is the correct behavior
    }

    public boolean inCurrentScope(K key) {
        return this.getEnvOfCurScope().containsKey(key);
    }

    // overwrites any existing entry for given key
    public void add(K key, V value) {
        if (this.curScope == -1) {
            System.out.println("WARN: Added to environment before beginning scope");
            this.beginScope();
        }
        this.getEnvOfCurScope().put(key, value);
    }

    public V lookup(K key) {
        return this.getEnvOfCurScope().get(key);
    }

    private HashMap<K, V> getEnvOfCurScope() {
        return this.env.get(this.curScope);
    }

    public void dumpEnv(String header) {
        System.out.println(header);
        for (HashMap<K, V> scopeEnv : this.env) {
            System.out.println("Map:"+scopeEnv);
        }
    }
}