package interpreter.utils;

import common.TupleGeneric;
import interpreter.value.IValue;

/**
 * Tuple Generic containing an IValue and an EnvStoreTuple
 */
public class ValueEnvStoreTuple extends TupleGeneric<IValue, EnvStoreTuple> {
    public ValueEnvStoreTuple(IValue left, EnvStoreTuple right) {
        super(left, right);
    }
}
