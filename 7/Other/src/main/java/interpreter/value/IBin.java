package interpreter.value;

import interpreter.utils.EnvStoreTuple;
import interpreter.utils.ValueEnvStoreTuple;

import java.util.List;

public interface IBin {
    ValueEnvStoreTuple call(List<IValue> vals, EnvStoreTuple tuple);
}
