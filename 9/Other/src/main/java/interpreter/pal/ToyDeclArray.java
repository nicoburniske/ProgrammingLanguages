package interpreter.pal;

import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.EnvStoreTuple;
import interpreter.value.IValue;
import interpreter.value.ValueLambdaClosure;

import java.util.List;

/**
 * Represents a sequence of declarations, with a scope
 */
public class PALDeclArray implements PAL {
    private List<Decl> declList;
    private PAL scope;

    public PALDeclArray(List<Decl> declList, PAL scope) {
        this.declList = declList;
        this.scope = scope;
    }

    @Override
    public ValueEnvStoreTuple interpret(EnvStoreTuple tuple) {
        EnvStoreTuple temp = tuple;
        // Iterate through all of the decls and interpret them.
        for (Decl d : this.declList) {
            temp = d.interpret(temp);
        }
        // Iterate over the decls again and resolve nested lambdas
        for (Decl d : this.declList) {
            IValue val = temp.lookup(d.getVar());
            // If the result of interpretation was a nested Lambda, then resolve outer lambda
            // Get the location (int) corresponding to the given decl's position in the store
            // Then update the stored value with result of applying the Lambda at the location using "set".
            if (val instanceof ValueLambdaClosure) {
                ValueLambdaClosure closure = (ValueLambdaClosure) val;
                int loc = temp.getLeft().get(d.getVar());
                temp = new EnvStoreTuple(temp.getLeft(), temp.getRight().set(loc, closure.apply(temp).getLeft()));
            }
        }
        // Interpret the scope using the updated Environment and Store
        ValueEnvStoreTuple newTuple = scope.interpret(temp);
        // we change the store but keep the same environment.
        return new ValueEnvStoreTuple(newTuple.getLeft(), new EnvStoreTuple(tuple.getLeft(), newTuple.getRight().getRight()));
    }
}
