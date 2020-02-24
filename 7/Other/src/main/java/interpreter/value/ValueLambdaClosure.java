package interpreter.value;

import interpreter.utils.EnvStoreTuple;
import interpreter.utils.ValueEnvStoreTuple;

public interface ValueLambdaClosure extends IValue{

    ValueEnvStoreTuple apply(EnvStoreTuple tup);

    @Override
    default String toJSONString() {
        return null;
    }
}
