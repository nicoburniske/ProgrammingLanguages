package interpreter.pal;

import interpreter.utils.CPSUtils;
import interpreter.utils.EnvStoreTuple;
import interpreter.utils.StopInterpretException;
import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.staticDistance.StaticDistanceEnvironment;
import interpreter.value.IValue;
import org.json.simple.JSONArray;

import java.util.Objects;
import java.util.Set;

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
        //TODO CPS or SplitExpression
        return new ToyStop(CPSUtils.toTestFormat(this.subexpression.CPS()));
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
