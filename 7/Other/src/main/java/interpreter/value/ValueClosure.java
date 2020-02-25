package interpreter.value;

import interpreter.pal.PAL;
import interpreter.pal.PALFunc;
import interpreter.pal.PALVar;
import interpreter.utils.EnvStoreTuple;
import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.env.Environment;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ValueClosure implements IValue {
    private PALFunc function;
    private Environment env;

    public ValueClosure(PALFunc function, Environment env) {
        this.function = function;
        this.env = env;
    }

    @Override
    public String toJSONString() {
        return "\"closure\"";
    }

    public ValueEnvStoreTuple apply(List<PAL> args, EnvStoreTuple tuple) {
        EnvStoreTuple temp = new EnvStoreTuple(this.env, tuple.getRight());
        List<PALVar> params = this.function.getParams();

        List<IValue> interpretedArgs = new ArrayList<>();
        ValueEnvStoreTuple argTuple;
        // Lookup params in given environment/store tuple
        for (PAL arg : args) {
            argTuple = arg.interpret(tuple);
            interpretedArgs.add(argTuple.getLeft());
            temp = argTuple.getRight();
        }
        // Add the params to the local environment
        for(int ii = 0; ii < args.size(); ii ++) {
            temp = temp.insert(params.get(ii), interpretedArgs.get(ii));
        }

        // Interpret with local env + params
        ValueEnvStoreTuple finalResult = this.function.apply(temp);

        // return the given environment (from tuple parameter) and the updated store
        return new ValueEnvStoreTuple(finalResult.getLeft(), new EnvStoreTuple(tuple.getLeft(), finalResult.getRight().getRight()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueClosure that = (ValueClosure) o;
        return function.equals(that.function) &&
                env.equals(that.env);
    }

    @Override
    public int hashCode() {
        return Objects.hash(function, env);
    }
}
