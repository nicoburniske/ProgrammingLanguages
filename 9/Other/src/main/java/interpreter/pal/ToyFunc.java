package interpreter.pal;

import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.EnvStoreTuple;
import interpreter.utils.env.Environment;
import interpreter.utils.staticDistance.StaticDistanceEnvironment;
import interpreter.utils.staticDistance.TupleSD;
import interpreter.value.ValueClosure;

import java.util.List;
import java.util.Objects;

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

    @Override
    public Toy computeStaticDistance(int currDepth, StaticDistanceEnvironment env) {
        for (int ii = 0; ii < params.size(); ii++) {
            ToyVar var = params.get(ii);
            env = env.put(var, new TupleSD(currDepth, ii));
        }
        return new ToyFunc(this.params, function.computeStaticDistance(currDepth + 1, env));
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToyFunc toyFunc = (ToyFunc) o;
        return params.equals(toyFunc.params) &&
                function.equals(toyFunc.function);
    }

    @Override
    public int hashCode() {
        return Objects.hash(params, function);
    }

    @Override
    public String toString() {
        return "ToyFunc{" +
                "params=" + params +
                ", function=" + function +
                '}';
    }
}
