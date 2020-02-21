package interpreter.utils.store;

import common.LookupTable;
import common.LookupTablePair;
import interpreter.value.IValue;


public class StorePair extends LookupTablePair<Integer, IValue> implements Store {
    public StorePair(Integer integer, IValue iValue, LookupTable<Integer, IValue> rest) {
        super(integer, iValue, rest);
    }
}
