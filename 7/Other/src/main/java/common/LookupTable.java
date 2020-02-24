package common;

import javafx.scene.shape.VLineTo;

/**
 * An Liked list lookup structure
 * @param <Key>
 * @param <Value>
 */
public interface LookupTable<Key, Value> {
    /**
     * Adds a new Key Value pair to the Ienv
     * @param key the key to be added
     * @param value the vlaue to be added
     * @return the new IEnv with the pair consed on to the head
     */
    public LookupTable<Key, Value> put(Key key, Value value);

    /**
     * Finds the first occurence of the {@param key} in the IEnv
     * @param key the key to be looked up
     * @return the corresponding value in the Ienv
     */
    public Value get(Key key);

    /**
     *
     * @return the size of the Lookup Table
     */
    public Integer getSize();

    /**
     * TODO
     * @param key
     * @param val
     * @return
     */
    LookupTable<Key, Value> set(Key key, Value val);
}
