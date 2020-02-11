package store;

import org.json.simple.JSONArray;
import org.json.simple.JSONAware;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Generic store class that will be used as both a store and an environment.
 *
 * @param <Key>
 * @param <Value>
 */
public class Store<Key, Value> implements JSONAware {
    private List<Key> keys;
    private List<Value> values;


    public Store() {
        this.keys = new ArrayList<>();
        this.values = new ArrayList<>();
    }
    public Store(Store<Key, Value> store) {
        this.keys = new ArrayList<>();
        this.values = new ArrayList<>();
        this.keys.addAll(store.getKeys());
        this.values.addAll(store.getValues());
    }


    public List<Key> getKeys() {
        return keys;
    }

    public List<Value> getValues() {
        return values;
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
     *  @param k
     * @param v
     * @return
     */
    public Value getThenSet(Key k, Value v) {
        return values.set(keys.indexOf(k), v);
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

    public int getSize() {
        return this.keys.size();
    }

    @Override
    public String toString() {
        return "Store{" +
                "keys=" + keys.stream().map(Object::toString)
                .collect(Collectors.joining(", ")) +
                ", values=" + values.stream().map(Object::toString)
                .collect(Collectors.joining(", ")) +
                '}';
    }

    @Override
    public String toJSONString() {
        JSONArray ret = new JSONArray();
        List<Value> valuesWithoutPrelude = values.subList(6, values.size());
        ret.addAll(valuesWithoutPrelude);
        return ret.toJSONString();
    }
}
