package interpreter.pal;

import interpreter.utils.EnvStoreTuple;
import interpreter.utils.store.Store;
import interpreter.value.IValue;
import interpreter.value.ValueLambdaClosure;

public class Decl {
    private PALVar var;
    private PAL rhs;

    public Decl(PALVar var, PAL rhs) {
        this.var = var;
        this.rhs = rhs;
    }

    public EnvStoreTuple interpret(EnvStoreTuple tuple) {
        if (rhs instanceof PALInt) {
            return tuple.insert(this.var, this.rhs.interpret(tuple));
        } else {
            return tuple.insert(this.var, (ValueLambdaClosure)(EnvStoreTuple env) -> this.rhs.interpret(env));
        }
    }

    public PALVar getVar() {
        return this.var;
    }
}
