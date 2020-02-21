package interpreter.pal;

import interpreter.utils.EnvStoreTuple;
import interpreter.value.IValue;

public class Decl {
    private PALVar var;
    private PAL rhs;

    public Decl(PALVar var, PAL rhs) {
        this.var = var;
        this.rhs = rhs;
    }

    public IValue interpret(EnvStoreTuple tuple) {
        return null;
    }
}
