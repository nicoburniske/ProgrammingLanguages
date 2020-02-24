package interpreter.utils.store;

import common.LookupTable;
import common.LookupTableEnd;
import common.LookupTablePair;
import interpreter.value.IValue;

import java.util.Objects;

public class Store {
    LookupTable<Integer, IValue> table;

    public Store(Integer integer, IValue value) {
        table = new LookupTablePair<>(integer, value, new LookupTableEnd<>());
    }
    public Store(LookupTable<Integer, IValue> table) {
        this.table = table;
    }
    public Store() {
        this.table = new LookupTableEnd<>();
    }

    public Store put(Integer key, IValue value) {
        return new Store(table.put(key, value));
    }

    public IValue get(Integer key) {
        return table.get(key);
    }

    public Store set(Integer key, IValue val) {
        return new Store(table.set(key, val));
    }
    public Integer getSize(){
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
}
