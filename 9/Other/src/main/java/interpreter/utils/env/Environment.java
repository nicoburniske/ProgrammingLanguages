package interpreter.utils.env;


import common.LookupTable;
import common.LookupTableEnd;
import common.LookupTablePair;
import interpreter.pal.ToyVar;

import java.util.Objects;

/**
 * Represents a non mutable environment. Composes a LookupTable<PALVar, Integer>
 */
public class Environment {
    LookupTable<ToyVar, Integer> table;

    public Environment(LookupTable<ToyVar, Integer> table) {
        this.table = table;
    }

    public Environment() {
        this.table = new LookupTableEnd<>();
    }

    public Environment(ToyVar palVar, Integer integer) {
        table = new LookupTablePair<>(palVar, integer, new LookupTableEnd<>());
    }

    public Environment put(ToyVar key, Integer value) {
        return new Environment(table.put(key, value));
    }

    public Integer get(ToyVar key) {
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
