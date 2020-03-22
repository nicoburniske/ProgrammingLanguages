package interpreter.pal;

import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.staticDistance.StaticDistanceEnvironment;
import interpreter.value.*;
import interpreter.utils.EnvStoreTuple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static interpreter.utils.RuntimeExceptions.ERROR_FUNCTION_EXPECTED;

/**
 * Represents the invocation of a PALFunc
 */
public class ToyCall implements Toy {
    Toy function;
    List<Toy> args;

    public ToyCall(Toy function, List<Toy> args) {
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
            //TODO: ensure that the reverse is necessary
            Collections.reverse(args);
            for (Toy arg : args) {
               //argTuple = arg.interpret(newTuple);
               EnvStoreTuple forInterpret = new EnvStoreTuple(tuple.getLeft(), temp.getRight());
               argTuple = arg.interpret(forInterpret);
               interpretedArgs.add(0, argTuple.getLeft());
               //newTuple = new EnvStoreTuple(newTuple.getLeft(), argTuple.getRight().getRight());
                temp = new EnvStoreTuple( temp.getLeft(), argTuple.getRight().getRight());
            }
            return ((ValuePrimop)val).apply(interpretedArgs, new EnvStoreTuple(newTuple.getLeft(), temp.getRight()));
        } else {
            throw new IllegalStateException(ERROR_FUNCTION_EXPECTED);
        }
    }

    @Override
    public Toy computeStaticDistance(int currDepth, StaticDistanceEnvironment env) {
        List<Toy> argsSD = this.args.stream().map(arg -> arg.computeStaticDistance(currDepth, env)).collect(Collectors.toList());
        Toy funcSD = this.function.computeStaticDistance(currDepth, env);
        return new ToyCall(funcSD, argsSD);
    }
}
