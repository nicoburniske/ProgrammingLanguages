package interpreter.value;

import interpreter.utils.EnvStoreTuple;

public interface ValueLambdaClosure extends IValue{

    IValue apply(EnvStoreTuple tup);

    @Override
    default String toJSONString() {
        return null;
    }
}
