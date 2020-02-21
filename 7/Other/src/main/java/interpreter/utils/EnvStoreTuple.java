package interpreter.utils;

import common.TupleGeneric;
import interpreter.utils.env.Environment;
import interpreter.utils.store.Store;

public class EnvStoreTuple extends TupleGeneric<Store, Environment> {
    public EnvStoreTuple(Store left, Environment right) {
        super(left, right);
    }
}
