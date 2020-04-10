package value;

import ast.WhileLang;
import utils.store.Store;

import java.util.Set;

/**
 * Represents the return type for an interpreted expression
 */
public interface IValue extends WhileLang {

    /**
     * Creates the String value of the {@link IValue}
     * @param store the current {@link Store}
     * @param acc the accumulator used to avoid infinite loops
     * @return a string representing the value
     */
    String toOutputString(Store store, Set<IValue> acc);
}
