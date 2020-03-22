package interpreter.utils.staticDistance;

import common.LookupTable;
import common.LookupTableEnd;
import common.LookupTablePair;
import interpreter.pal.ToyVar;

import java.util.Objects;

public class StaticDistanceEnvironment {
    LookupTable<ToyVar, TupleSD> table;

    public StaticDistanceEnvironment(ToyVar var, TupleSD tuple) {
        table = new LookupTablePair<>(var, tuple, new LookupTableEnd<>());
    }

    public StaticDistanceEnvironment(LookupTable<ToyVar, TupleSD> table) {
        this.table = table;
    }

    public StaticDistanceEnvironment() {
        this.table = new LookupTableEnd<>();
    }

    public StaticDistanceEnvironment put(ToyVar key, TupleSD value) {
        return new StaticDistanceEnvironment(table.put(key, value));
    }

    public TupleSD get(ToyVar key) {
        return table.get(key);
    }

    public StaticDistanceEnvironment set(ToyVar key, TupleSD val) {
        return new StaticDistanceEnvironment(table.set(key, val));
    }
    public Integer getSize(){
        return table.getSize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StaticDistanceEnvironment that = (StaticDistanceEnvironment) o;
        return table.equals(that.table);
    }

    @Override
    public int hashCode() {
        return Objects.hash(table);
    }
}
