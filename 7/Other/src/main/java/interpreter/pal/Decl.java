package interpreter.pal;

import interpreter.utils.EnvStoreTuple;
import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.env.Environment;
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
        if (this.rhs instanceof PALInt) {
            return tuple.insert(this.var, this.rhs.interpret(tuple).getLeft());
        } else {
            return tuple.insert(this.var,(ValueLambdaClosure)(EnvStoreTuple oldEnv) ->
                    new ValueEnvStoreTuple((ValueLambdaClosure)(EnvStoreTuple env) ->
                            ((PALFunc)this.rhs).interpret(env, oldEnv.getLeft()), tuple));
        }
    }

    public PALVar getVar() {
        return this.var;
    }
}
