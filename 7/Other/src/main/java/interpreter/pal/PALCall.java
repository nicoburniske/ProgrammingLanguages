package interpreter.pal;

import interpreter.value.IValue;
import interpreter.utils.EnvStoreTuple;

import java.util.List;

public class PALCall implements PAL {
    PAL function;
    List<PAL> args;

    public PALCall(PAL function, List<PAL> args) {
        this.function = function;
        this.args = args;
    }

    @Override
    public IValue interpret(EnvStoreTuple tuple) {
        return null;
    }
}
