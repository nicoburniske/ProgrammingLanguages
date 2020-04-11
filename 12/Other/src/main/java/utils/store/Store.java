package utils.store;

import ast.WhileLang;
import ast.stmt.frame.IFrame;
import utils.EnvStoreTuple;
import utils.exceptions.StoreSizeException;
import utils.table.LookupTable;
import utils.table.LookupTableEnd;
import utils.table.LookupTablePair;
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

    public Store(LookupTable<Location, IValue> table, int maxSize) {
        this.table = table;
        setMaxSize(maxSize);
    }

    public Store(int maxSize) {
        this.table = new LookupTableEnd<>();
        setMaxSize(maxSize);
    }

    public Store put(Location key, IValue value, EnvStoreTuple tuple, Stack<IFrame> stack , WhileLang control) {
        if(table.getSize() < maxSize) {
            return new Store(table.put(key, value), maxSize);
        }
        else {
            //TODO:Signal an error
            return (Heap.findRoots(control, tuple.getLeft(), stack, tuple.getRight())).put(key, value, tuple, stack, control);
        }
    }


    public Store put(Location key, IValue value) {
        if(table.getSize() < maxSize) {
            return new Store(table.put(key, value), maxSize);
        }
        else {
            //TODO:Signal an error
            throw new StoreSizeException();
        }
    }

    public IValue get(Location key) {
        return table.get(key);
    }

    public Store set(Location key, IValue val) {
        return new Store(table.set(key, val), maxSize);
    }

    public Store insert(IValue val, EnvStoreTuple tuple, Stack<IFrame> stack , WhileLang control) {
        return this.put(new Location(this.table.getSize()), val, tuple, stack,control);
    }

    public int getSize(){
        return table.getSize();
    }

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

    public boolean containsKey(Location reference) {
        return table.containsKey(reference);
    }

    public int getMaxSize() {
        return maxSize;
    }
}
