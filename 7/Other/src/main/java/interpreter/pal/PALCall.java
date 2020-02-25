package interpreter.pal;

import interpreter.utils.ValueEnvStoreTuple;
import interpreter.value.*;
import interpreter.utils.EnvStoreTuple;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the invocation of a PALFunc
 */
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
        EnvStoreTuple newTuple = new EnvStoreTuple(temp.getLeft(), resultTuple.getRight().getRight());
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
        } else {
            // should never be called
            return new ValueEnvStoreTuple(new ValueInt(-666L), tuple);
        }
    }
}
