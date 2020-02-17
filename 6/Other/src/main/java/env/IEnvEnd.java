package env;

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
}
