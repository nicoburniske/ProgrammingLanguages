package interpreter.pal;

import interpreter.utils.ValueEnvStoreTuple;
import interpreter.value.IValue;
import interpreter.utils.EnvStoreTuple;
import interpreter.value.ValueClosure;
import interpreter.value.ValueLambdaClosure;
import interpreter.value.ValuePrimop;

import java.util.ArrayList;
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
        ValueEnvStoreTuple resultTuple = this.function.interpret(temp);
        IValue val = resultTuple.getLeft();
        EnvStoreTuple newTuple = new EnvStoreTuple(tuple.getLeft(), resultTuple.getRight().getRight());
        if (val instanceof ValueClosure) {
            return ((ValueClosure) val).apply(args, newTuple);
        } else if (val instanceof ValueLambdaClosure) {
            ValueEnvStoreTuple result = ((ValueLambdaClosure) val).apply(newTuple);
            newTuple = result.getRight();
            IValue resultValue = result.getLeft();
            // if the result is a value closure we must then try and apply the function with the current args, otherwise return the result as is.
            return (resultValue instanceof ValueClosure) ? ((ValueClosure) resultValue).apply(args, newTuple) : result;
        } else if (val instanceof ValuePrimop) {
            List<IValue> interpretedArgs = new ArrayList<>();
            ValueEnvStoreTuple argTuple;
            for (PAL arg : args) {
               argTuple = arg.interpret(newTuple);
               interpretedArgs.add(argTuple.getLeft());
               newTuple = new EnvStoreTuple(newTuple.getLeft(), argTuple.getRight().getRight());
            }
            return ((ValuePrimop)val).apply(interpretedArgs, newTuple);
            // TODO: there may be an issue here regarding the lack of mutation in the store.
            // If an argument to the primop is
            // return ((ValuePrimop) val).apply(args.stream().map(a -> a.interpret(temp).getLeft()).collect(Collectors.toList()), temp);
        } else {
            throw new IllegalStateException(val.getClass().getName());
        }
    }
}
