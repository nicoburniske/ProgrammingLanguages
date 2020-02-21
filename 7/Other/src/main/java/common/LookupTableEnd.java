package common;

/**
 * End of the List
 * @param <Key>
 * @param <Value>
 */
public class LookupTableEnd<Key, Value> implements LookupTable<Key, Value> {
    @Override
    public LookupTable put(Key key, Value value) {
        return new LookupTablePair(key, value, this);
    }

    @Override
    public Value get(Key key) {
        return null;
    }

    @Override
    public Integer getSize() {
        return 0;
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
