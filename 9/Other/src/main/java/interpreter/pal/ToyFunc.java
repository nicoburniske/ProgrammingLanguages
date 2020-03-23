package interpreter.pal;

import interpreter.utils.CPSUtils;
import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.EnvStoreTuple;
import interpreter.utils.env.Environment;
import interpreter.utils.staticDistance.StaticDistanceEnvironment;
import interpreter.utils.staticDistance.TupleSD;
import interpreter.value.ValueClosure;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public Toy getFunction() {
        return function;
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

    @Override
    public Toy computeStaticDistance(int currDepth, StaticDistanceEnvironment env) {
        for (int ii = 0; ii < params.size(); ii++) {
            ToyVar var = params.get(ii);
            env = env.put(var, new TupleSD(currDepth, ii));
        }
        return new ToyFunc(this.params.stream().map(param -> new ToyVar("_")).collect(Collectors.toList()), function.computeStaticDistance(currDepth + 1, env));
    }

    @Override
    public Toy CPS() {
        List<ToyVar> params2 = new ArrayList<>(this.params);
        params2.add(0, CPSUtils.K);
        return new ToyFunc(params2, this.function.splitExpression());
    }

    @Override
    public Toy splitExpression() {
        List<ToyVar> params2 = new ArrayList<>(this.getParams());
        params2.add(0, CPSUtils.K);
        return new ToyCall(CPSUtils.K, new ToyFunc(params2, this.function.splitExpression()));
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

    @Override
    public String toJSONString() {
        JSONArray ret = new JSONArray();
        ret.add("fun*");
        ret.add(this.params);
        ret.add(this.function);
        return ret.toJSONString();
    }
}
