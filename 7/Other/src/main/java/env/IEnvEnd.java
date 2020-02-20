package env;

/**
 * End of the List
 * @param <Key>
 * @param <Value>
 */
public class IEnvEnd<Key, Value> implements IEnv<Key, Value> {
    @Override
    public IEnv put(Key key, Value value) {
        return new IEnvPair(key, value, this);
    }

    @Override
    public Value get(Key key) {
        return null;
        //TODO:Maybe throw the error here
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof IEnvEnd;
    }

    @Override
    public String toString() {
        return "END";
    }
}
