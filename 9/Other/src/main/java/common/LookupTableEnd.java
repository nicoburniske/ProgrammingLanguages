package common;

import static interpreter.utils.RuntimeExceptions.ERROR_UNDECLARED_VARIABLE_TEMPLATE;

/**
 * End of the List
 *
 * @param <Key>
 * @param <Value>
 */
public class LookupTableEnd<Key, Value> implements LookupTable<Key, Value> {
    @Override
    public LookupTable<Key, Value> put(Key key, Value value) {
        return new LookupTablePair<Key, Value>(key, value, this);
    }

    @Override
    public Value get(Key key) {
        throw new IllegalStateException(String.format(ERROR_UNDECLARED_VARIABLE_TEMPLATE, key));
    }

    @Override
    public Integer getSize() {
        return 0;
    }

    @Override
    public LookupTable<Key, Value> set(Key key, Value val) {
        return this;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof LookupTableEnd;
    }

    @Override
    public String toString() {
        return "END";
    }
}
