package interpreter.value;

import interpreter.utils.EnvStoreTuple;
import interpreter.utils.ValueEnvStoreTuple;

import java.util.List;

/**
 * Represents a store passing binary operator
 */
public interface IBin {
    ValueEnvStoreTuple call(List<IValue> vals, EnvStoreTuple tuple);
}
