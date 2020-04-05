package utils;

import utils.env.Environment;
import utils.store.Store;
import value.IValue;

public class ValueEnvStoreTuple extends Tuple<IValue, EnvStoreTuple>{
    public ValueEnvStoreTuple(IValue left, EnvStoreTuple right) {
        super(left, right);
    }
    public Environment getEnv() {
        return this.right.getLeft();
    }
    public Store getStore() {
        return this.right.getRight();
    }
}
