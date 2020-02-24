package interpreter.utils;

import common.TupleGeneric;
import interpreter.value.IValue;

public class ValueEnvStoreTuple extends TupleGeneric<IValue, EnvStoreTuple> {
    public ValueEnvStoreTuple(IValue left, EnvStoreTuple right) {
        super(left, right);
    }
}
