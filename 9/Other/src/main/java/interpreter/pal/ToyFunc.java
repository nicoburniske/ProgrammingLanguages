package interpreter.pal;

import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.EnvStoreTuple;
import interpreter.utils.env.Environment;
import interpreter.value.ValueClosure;

import java.util.List;

/**
 * Represents a function
 */
public class ToyFunc implements Toy {
    private List<ToyVar> params;
    private Toy function;

    public ToyFunc(List<ToyVar> params, Toy function) {
        this.params = params;
        this.function = function;
    }

    @Override
    public ValueEnvStoreTuple interpret(EnvStoreTuple tuple) {
        return new ValueEnvStoreTuple(new ValueClosure(this, tuple.getLeft()), tuple);
    }

    /**
     * Allows the construction of a ValueClosure with a different environment. Only usage is in Decl
     */
    public ValueEnvStoreTuple interpret(EnvStoreTuple tuple, Environment env) {
        return new ValueEnvStoreTuple(new ValueClosure(this, env), tuple);
    }

    public List<ToyVar> getParams() {
        return params;
    }

    public ValueEnvStoreTuple apply(EnvStoreTuple tuple) {
        return this.function.interpret(tuple);
    }
}
