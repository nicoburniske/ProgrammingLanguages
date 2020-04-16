package utils.store;

import ast.WhileLang;
import ast.stmt.frame.IFrame;
import utils.EnvStoreTuple;
import utils.exceptions.StoreSizeException;
import utils.table.LookupTable;
import utils.table.LookupTableEnd;
import value.IValue;
import value.Location;

import java.util.Objects;
import java.util.Stack;

/**
 * Represents a non mutable Store. Composes a {@link LookupTable<Location,IValue>}
 */
public class Store {
    LookupTable<Location, IValue> table;
    private int maxSize;
    private int counter;

    public Store(LookupTable<Location, IValue> table, int maxSize, int counter) {
        this.table = table;
        setMaxSize(maxSize);
        this.counter = counter;
    }

    public Store(int maxSize) {
        this.table = new LookupTableEnd<>();
        setMaxSize(maxSize);
        this.counter = 0;
    }


    /**
     * This function puts a new (Key,Value) pair into the store. This insertion may trigger garbage collection if the
     * Store is out of space.
     * @param key The {@link Location} being added to the Store
     * @param value the {@link IValue} being added to the Store
     * @param tuple used for garbage collection
     * @param stack used for garbage collection
     * @param control used for garbage collection
     * @return the new and updated Store
     */
    public Store putWithGarbageCollection(Location key, IValue value, EnvStoreTuple tuple, Stack<IFrame> stack , WhileLang control) {
        if(table.getSize() < maxSize) {
            return new Store(table.put(key, value), maxSize, this.counter + 1);
        }
        else {
            //TODO:Signal an error
            return (Heap.findRoots(control, tuple.getLeft(), stack, tuple.getRight())).putWithGarbageCollection(key, value, tuple, stack, control);
        }
    }

    /**
     * This function will insert a new key value pair into the store. If the store is full it will throw
     * an {@link StoreSizeException} and will not attempt to garbage collect. Do Not use this unless you know what
     * you are doing.
     * @param key The key to be added to the {@link Store}
     * @param value The value to be added to the {@link Store}
     * @return the updated {@link Store}
     */
    public Store put(Location key, IValue value) {
        if(table.getSize() < maxSize) {
            return new Store(table.put(key, value), maxSize, this.counter + 1);
        }
        else {
            //TODO:Signal an error
            throw new StoreSizeException();
        }
    }

    /**
     * gets the specified {@link Location}'s {@link Value}
     * @param key
     * @return
     */
    public IValue get(Location key) {
        return table.get(key);
    }

    /**
     * Sets a specified {@link Location}'s corresponding value to {@param val}
     * @param key the key whos value will be changed
     * @param val the new value
     * @return the updated Store
     */
    public Store set(Location key, IValue val) {
        return new Store(table.set(key, val), maxSize, this.counter + 1);
    }

    /**
     * This function will insert a new value into the next available {@link Location} in the store. If the {@link Store}
     * need garbage collection this funtion will attempt to preform it.
     * @param val the {@link IValue} that is being inserted into the store
     * @param tuple used fore garbage collection
     * @param stack used fore garbage collection
     * @param control used fore garbage collection
     * @return the updated store
     */
    public Store insertWithGarbageCollection(IValue val, EnvStoreTuple tuple, Stack<IFrame> stack , WhileLang control) {
        return this.putWithGarbageCollection(new Location(this.counter), val, tuple, stack,control);
    }

    /**
     *
     * @return the size of the store
     */
    public int getSize(){
        return table.getSize();
    }

    /**
     * Sets the max size of the store
     */
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return table.equals(store.table);
    }

    @Override
    public int hashCode() {
        return Objects.hash(table);
    }

    @Override
    public String toString() {
        return table.toString();
    }

    /**
     * Does the store contain this {@param reference}
     */
    public boolean containsKey(Location reference) {
        return table.containsKey(reference);
    }

    /**
     * @return the Max size of the Store
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * @return The next index to use for inserting something into a store
     */
    public int getCounter() {
        return this.counter;
    }

    /**
     * Sets the index to use to insert something into the store
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }
}
