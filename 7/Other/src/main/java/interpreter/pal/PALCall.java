package interpreter.pal;

import interpreter.utils.ValueEnvStoreTuple;
import interpreter.value.IValue;
import interpreter.utils.EnvStoreTuple;
import interpreter.value.ValueClosure;
import interpreter.value.ValueLambdaClosure;
import interpreter.value.ValuePrimop;

import java.util.List;
import java.util.stream.Collectors;

public class PALCall implements PAL {
    PAL function;
    List<PAL> args;

    public PALCall(PAL function, List<PAL> args) {
        this.function = function;
        this.args = args;
    }

    @Override
    public ValueEnvStoreTuple interpret(EnvStoreTuple tuple) {
        EnvStoreTuple temp = tuple;
        IValue val = this.function.interpret(temp).getLeft();
        if (val instanceof ValueClosure) {
           return ((ValueClosure) val).apply(args, temp);
        } else if (val instanceof ValueLambdaClosure) {
            ValueEnvStoreTuple result =  ((ValueLambdaClosure)val).apply(temp);
            IValue resultValue = result.getLeft();
            // if the result is a value closure we must then try and apply the function with the current args, otherwise return the result as is.
            return (resultValue instanceof ValueClosure) ? ((ValueClosure) resultValue).apply(args, temp) : result;
        } else if (val instanceof ValuePrimop){
            return ((ValuePrimop)val).apply(args.stream().map( a -> a.interpret(temp).getLeft()).collect(Collectors.toList()), temp);
        } else {
            throw new IllegalStateException(val.getClass().getName());
        }
    }
}
