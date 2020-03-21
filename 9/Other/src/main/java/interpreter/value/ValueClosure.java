package interpreter.value;

import interpreter.pal.Toy;
import interpreter.pal.ToyFunc;
import interpreter.pal.ToyVar;
import interpreter.utils.EnvStoreTuple;
import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.env.Environment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a Function stored in the Store
 */
public class ValueClosure implements IValue {
    private ToyFunc function;
    private Environment env;

    public ValueClosure(ToyFunc function, Environment env) {
        this.function = function;
        this.env = env;
    }

    @Override
    public String toJSONString() {
        return "\"closure\"";
    }

    /**
     * Applies the function with the given list of arguments using the environment held as a field
     * @param args The arguments to be used
     * @param tuple the tuple containing the current environment and store
     * @return (1) the Value corresponding to the application of this function with the supplied args, and (2) the updated EnvStoreTuple
     */
    public ValueEnvStoreTuple apply(List<Toy> args, EnvStoreTuple tuple) {
        // Temp is the EnvStore tuple that we will use in order to use the environment of the function
        // as well as the current store
        EnvStoreTuple temp = new EnvStoreTuple(this.env, tuple.getRight());
        List<ToyVar> params = this.function.getParams();

        List<IValue> interpretedArgs = new ArrayList<>();
        ValueEnvStoreTuple argTuple;
        // Lookup params in given environment/store tuple
        for (Toy arg : args) {
            EnvStoreTuple forInterpret = new EnvStoreTuple(tuple.getLeft(), temp.getRight());
            argTuple = arg.interpret(forInterpret);
            interpretedArgs.add(argTuple.getLeft());
            temp = new EnvStoreTuple(temp.getLeft(), argTuple.getRight().getRight());
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
