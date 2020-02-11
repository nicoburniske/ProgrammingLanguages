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
    int counter;

    /**
     * Constructor For {@link Store}
     * initalizes it to empty.
     */
    public Store() {
        this.keys = new ArrayList<>();
        this.values = new ArrayList<>();
        counter = 0;
    }

    /**
     * A copy constructor for a store.
     *
     * @param store the store to be copied
     */
    public Store(Store<Key, Value> store) {
        this.keys = new ArrayList<>();
        this.values = new ArrayList<>();
        this.keys.addAll(store.getKeys());
        this.values.addAll(store.getValues());
        counter = store.counter;
    }

    /**
     * Gets the keys in the {@link Store}
     *
     * @return the keys
     */
    public List<Key> getKeys() {
        return keys;
    }

    /**
     * gets the values in {@link Store}
     *
     * @return the values
     */
    public List<Value> getValues() {
        return values;
    }

    /**
     * This function searches through the store returns the most recently added
     * value that whose key is equal to {@param k}
     *
     * @param k the key to look for
     * @return the matching value to the specified {@param k}
     */
    public Value get(Key k) {
        try {
            return values.get(keys.lastIndexOf(k));
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }


    /**
     * Sets the value of the given {@param k} in the store to the given {@param v}
     *
     * @param k the key whose value will be chaned
     * @param v the new value for the key
     * @return the value that was contained in the store before the assignment
     */
    public Value getThenSet(Key k, Value v) {
        return values.set(keys.indexOf(k), v);
    }

    /**
     * Adds a new entry ({@param k}, {@param v}) into the store to and increments the counter
     *
     * @param k the key of the new value in the store
     * @param v the value of the new key in the store
     */
    public void put(Key k, Value v) {
        keys.add(k);
        values.add(v);
        counter++;
    }

    /**
     * Removes the most recent element off the store
     */
    public void pop() {
        this.keys.remove(this.keys.size() - 1);
        this.values.remove(this.values.size() - 1);
    }

    /**
     * This is used to keep track what number can be used for insertion
     * see StoreUtils.insertIntoStore
     *
     * @return the next value that canbe used as a key
     */
    public int getSize() {
        return counter;
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

    /**
     * This functions converts {@link Store} into JSON for
     * printing using the {@link JSONAware} library
     *
     * @return A JSON formatted String
     */
    @Override
    public String toJSONString() {
        JSONArray ret = new JSONArray();
        List<Value> valuesWithoutPrelude = values.subList(6, values.size());
        ret.addAll(valuesWithoutPrelude);
        return ret.toJSONString();
    }
}
