package typecheck.env;

import java.util.Objects;

/**
 * A pair in the list
 * @param <Key>
 * @param <Value>
 */
public class IEnvPair<Key, Value> implements IEnv<Key, Value> {
    Key key;
    Value value;
    IEnv<Key, Value> rest;

    public IEnvPair(Key key, Value value, IEnv<Key,Value> rest) {
        this.key = key;
        this.value = value;
        this.rest = rest;
    }

    @Override
    public IEnv put(Key key, Value value) {
        return new IEnvPair(key, value, this);
    }

    @Override
    public Value get(Key key) {
        if(this.key.equals(key)) {
            return value;
        } else {
            return rest.get(key);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IEnvPair<?, ?> iEnvPair = (IEnvPair<?, ?>) o;
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
        return "IEnvPair{" +
                "key=" + key +
                ", value=" + value +
                ", rest=" + rest +
                '}';
    }
}
