package interpreter.utils.env;


import common.LookupTable;
import common.LookupTableEnd;
import common.LookupTablePair;
import interpreter.pal.PALVar;
import interpreter.utils.store.Store;
import interpreter.value.IValue;

import java.util.Objects;

public class Environment {
    LookupTable<PALVar, Integer> table;

    public Environment( LookupTable<PALVar, Integer> table) {
        this.table = table;
    }
    public Environment() {
        this.table = new LookupTableEnd<>();
    }
    public Environment(PALVar palVar, Integer integer) {
        table = new LookupTablePair<>(palVar, integer, new LookupTableEnd<>());
    }

    public Environment put(PALVar key, Integer value) {
        return new Environment(table.put(key, value));
    }

    public Integer get(PALVar key) {
        return table.get(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Environment that = (Environment) o;
        return table.equals(that.table);
    }

    @Override
    public int hashCode() {
        return Objects.hash(table);
    }
}
