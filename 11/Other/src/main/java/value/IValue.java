package value;

import ast.WhileLang;
import utils.store.Store;

import java.util.Set;

public interface IValue extends WhileLang {
    String toOutputString(Store store, Set<IValue> acc);
}
