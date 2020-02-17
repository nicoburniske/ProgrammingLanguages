package env;

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
}
