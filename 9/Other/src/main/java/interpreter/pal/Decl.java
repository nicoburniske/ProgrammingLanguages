package interpreter.pal;

import interpreter.utils.EnvStoreTuple;
import interpreter.utils.ValueEnvStoreTuple;
import interpreter.value.ValueLambdaClosure;

/**
 * Represents an individual declaration where the rhs is constrained to being one of:
 * - PALFunc
 * - PALInt
 */
public class Decl {
    private ToyVar var;
    private Toy rhs;

    public Decl(ToyVar var, Toy rhs) {
        this.var = var;
        this.rhs = rhs;
    }

    /**
     * Adds new variables to the supplied EnvStoreTuple
     * @return the new EnvStoreTuple with the current decl entry appended to the given EnvStoreTuple.
     * DOES NOT MUTATE
     */
    public EnvStoreTuple interpret(EnvStoreTuple tuple) {
        if (this.rhs instanceof ToyInt) {
            return tuple.insert(this.var, this.rhs.interpret(tuple).getLeft());
        } else if (this.rhs instanceof ToyFunc) {
            return
                    // Lambda 1: Holds a temporary value to be evaluated after all Decls in PALDeclArray have been entered into the list
                    // Allows for the decls in the same PALDeclArray to reference each other, no matter their order
                    tuple.insert(this.var,(ValueLambdaClosure)(EnvStoreTuple oldEnv) ->
                            // Lambda 2: Holds the function to be applied upon access to allow for recursion
                        new ValueEnvStoreTuple((ValueLambdaClosure)(EnvStoreTuple env) ->
                            ((ToyFunc)this.rhs).interpret(env, oldEnv.getLeft()), tuple));
        } else {
           // THIS SHOULD NEVER BE CALLED -666
            throw new IllegalStateException("This is a invalid test now");
        }
    }

    public ToyVar getVar() {
        return this.var;
    }

    public Toy getRhs() {
        return this.rhs;
    }
}
