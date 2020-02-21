package interpreter.pal;

import interpreter.value.IValue;
import interpreter.utils.EnvStoreTuple;

public class PALVar implements PAL {
    private String var;

    public PALVar(String var) {
        this.var = var;
    }

    @Override
    public IValue interpret(EnvStoreTuple tuple) {
        return tuple.lookup(this);
    }
}
