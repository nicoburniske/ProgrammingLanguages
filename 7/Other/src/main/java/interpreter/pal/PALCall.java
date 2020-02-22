package interpreter.pal;

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
    public IValue interpret(EnvStoreTuple tuple) {
        IValue val = this.function.interpret(tuple);
        if (val instanceof ValueClosure) {
           return ((ValueClosure) val).apply(args, tuple);
        } else if (val instanceof ValueLambdaClosure) {
            IValue result =  ((ValueLambdaClosure)val).apply(tuple);
            // if the result is a value closure we must then try and apply the function with the current args, otherwise return the result as is.
            return (result instanceof ValueClosure) ? ((ValueClosure) result).apply(args, tuple) : result;
        } else if (val instanceof ValuePrimop){
            return ((ValuePrimop)val).apply(args.stream().map( a -> a.interpret(tuple)).collect(Collectors.toList()));
        } else {
            throw new IllegalStateException(val.getClass().getName());
        }
    }
}
