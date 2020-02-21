package interpreter.value;

import interpreter.pal.PAL;
import interpreter.pal.PALFunc;
import interpreter.pal.PALVar;
import interpreter.utils.EnvStoreTuple;
import interpreter.utils.env.Environment;

import java.util.List;
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
        return null;
    }

    public IValue apply(List<PAL> args, EnvStoreTuple tuple) {
        List<PALVar> params = this.function.getParams();
        EnvStoreTuple finalTuple = tuple;
        List<IValue> argsVal = args.stream().map(e -> e.interpret(finalTuple)).collect(Collectors.toList());
        for(int ii = 0; ii < args.size(); ii ++) {
            tuple = tuple.insert(params.get(ii), argsVal.get(ii));
        }
        return this.function.apply(tuple);
    }
}
