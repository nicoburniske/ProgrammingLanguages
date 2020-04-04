package utils.store;

import utils.table.LookupTable;
import utils.table.LookupTableEnd;
import utils.table.LookupTablePair;
import value.IValue;
import value.Location;

import java.util.Objects;

/**
 * Represents a non mutable Store. Composes a LookupTable<Location, IValue>
 */
public class Store {
    LookupTable<Location, IValue> table;

    public Store(LookupTable<Location, IValue> table) {
        this.table = table;
    }

    public Store(Location key, IValue value) {
        table = new LookupTablePair<>(key, value, new LookupTableEnd<>());
    }
    public Store() {
        this.table = new LookupTableEnd<>();
    }

    public Store put(Location key, IValue value) {
        return new Store(table.put(key, value));
    }

    public IValue get(Location key) {
        return table.get(key);
    }

    public Store set(Location key, IValue val) {
        return new Store(table.set(key, val));
    }
    public int getSize(){
        return table.getSize();
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
}
