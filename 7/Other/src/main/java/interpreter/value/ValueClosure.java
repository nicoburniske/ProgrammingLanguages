package interpreter.value;

import interpreter.pal.PAL;
import interpreter.pal.PALFunc;
import interpreter.pal.PALVar;
import interpreter.utils.EnvStoreTuple;
import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.env.Environment;
import org.json.simple.JSONObject;

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
        EnvStoreTuple finalTuple = temp;
        List<IValue> argsVal = args.stream().map(e -> e.interpret(finalTuple).getLeft()).collect(Collectors.toList());
        for(int ii = 0; ii < args.size(); ii ++) {
            temp = temp.insert(params.get(ii), argsVal.get(ii));
        }
        return new ValueEnvStoreTuple(this.function.apply(temp).getLeft(), tuple);
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
