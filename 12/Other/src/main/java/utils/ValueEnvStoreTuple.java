package utils;

import utils.env.Environment;
import utils.store.Store;
import value.IValue;

/**
 * Represent s a {@link Tuple} with an {@link IValue} and an {@link EnvStoreTuple}
 */
public class ValueEnvStoreTuple extends Tuple<IValue, EnvStoreTuple>{
    public ValueEnvStoreTuple(IValue left, EnvStoreTuple right) {
        super(left, right);
    }

    /**
     * Abstraction to easily get the {@link Environment}
     */
    public Environment getEnv() {
        return this.right.getLeft();
    }

    /**
     * Abstraction to easily get the {@link Store}
     */
    public Store getStore() {
        return this.right.getRight();
    }
}
