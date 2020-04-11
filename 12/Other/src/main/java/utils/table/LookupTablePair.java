package utils.table;

import java.util.List;
import java.util.Objects;

/**
 * An entry in the list
 *
 * @param <Key>
 * @param <Value>
 */
public class LookupTablePair<Key, Value> implements LookupTable<Key, Value> {
    Key key;
    Value value;
    LookupTable<Key, Value> rest;

    public LookupTablePair(Key key, Value value, LookupTable<Key, Value> rest) {
        this.key = key;
        this.value = value;
        this.rest = rest;
    }

    @Override
    public LookupTable<Key, Value> put(Key key, Value value) {
        return new LookupTablePair<Key, Value>(key, value, this);
    }

    @Override
    public Value get(Key key) {
        if (this.key.equals(key)) {
            return value;
        } else {
            return rest.get(key);
        }
    }

    @Override
    public Integer getSize() {
        return 1 + rest.getSize();
    }

    @Override
    public LookupTable<Key, Value> set(Key key, Value val) {
        if (this.key.equals(key)) {
            return new LookupTablePair<Key, Value>(this.key, val, this.rest);
        } else {
            return new LookupTablePair<Key, Value>(this.key, this.value, rest.set(key, val));
        }
    }

    @Override
    public boolean containsKey(Key reference) {
        if(this.key.equals(reference)) {
            return true;
        } else {
            return this.rest.containsKey(reference);
        }
    }

    @Override
    public List<Value> getValuesHelper(List<Value> valuesAcc) {
        valuesAcc.add(value);
        return valuesAcc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LookupTablePair<?, ?> iEnvPair = (LookupTablePair<?, ?>) o;
        return key.equals(iEnvPair.key) &&
                value.equals(iEnvPair.value) &&
                rest.equals(iEnvPair.rest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value, rest);
    }

    @Override
    public String toString() {
        return "[" + key.toString() + "," +
                value.toString() + "]" +
                ", " + rest.toString();
    }
}
