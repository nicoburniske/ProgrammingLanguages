package store;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic store class that will be used as both a store and an environment.
 *
 * @param <Key>
 * @param <Value>
 */
public class Store<Key, Value> {
    private List<Key> keys;
    private List<Value> values;

    public Store() {
        this.keys = new ArrayList<>();
        this.values = new ArrayList<>();
    }

    /**
     * @param k
     * @return the value associated with the given key.
     */
    public Value get(Key k) {
        try {
            return values.get(keys.lastIndexOf(k));
        } catch (IndexOutOfBoundsException e) {
            return null; //TODO: make sure this works
        }
    }

    /**
     * Records a new association within the store.
     */
    public void put(Key k, Value v) {
        keys.add(k);
        values.add(v);
    }

    /**
     * Removes the most recent entry.
     */
    public void pop() {
        this.keys.remove(this.keys.size() - 1);
        this.values.remove(this.values.size() - 1);
    }
}
