package interpreter.value;

import interpreter.utils.EnvStoreTuple;
import interpreter.utils.ValueEnvStoreTuple;

/**
 * Represents a Lambda containing a closure
 */
public interface ValueLambdaClosure extends IValue{

    ValueEnvStoreTuple apply(EnvStoreTuple tup);

    @Override
    default String toJSONString() {
        return "\"closure\"";
    }
}
