package interpreter.pal;

import interpreter.utils.CPSUtils;
import interpreter.utils.EnvStoreTuple;
import interpreter.utils.StopInterpretException;
import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.staticDistance.StaticDistanceEnvironment;
import interpreter.value.IValue;
import org.json.simple.JSONArray;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

/**
 * A {@link Toy} that represents a CPS construct which eliminates its continuation
 * and then evaluates its sub-expression to obtain the final answer.
 */
public class ToyStop implements Toy {
    Toy subexpression;

    public ToyStop(Toy subexpression) {
        this.subexpression = subexpression;
    }

    @Override
    public ValueEnvStoreTuple interpret(EnvStoreTuple tuple) {
        IValue val = this.subexpression.interpret(tuple).getLeft();
        throw new StopInterpretException(val);
    }

    @Override
    public Toy computeStaticDistance(int currDepth, StaticDistanceEnvironment env) {
        return new ToyStop(this.subexpression.computeStaticDistance(currDepth, env));
    }

    @Override
    public Toy splitExpression() {
        return new ToyCall(this.subexpression.CPS(), new ToyFunc(Arrays.asList(CPSUtils.identity), new ToyStop(CPSUtils.identity)));
    }

    @Override
    public void getAllNames(Set<String> names) {
        this.subexpression.getAllNames(names);
    }

    @Override
    public String toJSONString() {
        JSONArray arr = new JSONArray();
        arr.add("stop");
        arr.add(this.subexpression);
        return arr.toJSONString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToyStop toyStop = (ToyStop) o;
        return Objects.equals(subexpression, toyStop.subexpression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subexpression);
    }
}
