package interpreter.pal;

import interpreter.utils.CPSUtils;
import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.staticDistance.StaticDistanceEnvironment;
import interpreter.value.*;
import interpreter.utils.EnvStoreTuple;
import org.json.simple.JSONArray;

import java.util.*;
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

    public ToyCall(Toy function, Toy arg) {
        this.function = function;
        this.args = Arrays.asList(arg);
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

    @Override
    public Toy splitExpression() {
        List<Toy> argsCopy = this.args.stream().map(Toy::splitExpression).collect(Collectors.toList());
        argsCopy.add(0, CPSUtils.K);
        return new ToyCall(this.function.splitExpression(), argsCopy);
    }

    @Override
    public void getAllNames(Set<String> names) {
        this.args.forEach(arg -> arg.getAllNames(names));
        this.function.getAllNames(names);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToyCall toyCall = (ToyCall) o;
        return function.equals(toyCall.function) &&
                args.equals(toyCall.args);
    }

    @Override
    public int hashCode() {
        return Objects.hash(function, args);
    }

    @Override
    public String toString() {
        return "ToyCall{" +
                "function=" + function +
                ", args=" + args +
                '}';
    }

    @Override
    public String toJSONString() {
        JSONArray arr = new JSONArray();
        arr.add("call");
        arr.add(this.function);
        arr.addAll(this.args);
        return arr.toJSONString();
    }
}
