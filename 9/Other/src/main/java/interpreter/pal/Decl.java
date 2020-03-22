package interpreter.pal;

import interpreter.utils.CPSUtils;
import interpreter.utils.EnvStoreTuple;
import interpreter.utils.ValueEnvStoreTuple;
import interpreter.value.ValueLambdaClosure;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents an individual declaration where the rhs is constrained to being one of:
 * - PALFunc
 * - PALInt
 */
public class Decl implements JSONAware {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Decl decl = (Decl) o;
        return var.equals(decl.var) &&
                rhs.equals(decl.rhs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(var, rhs);
    }

    @Override
    public String toString() {
        return "Decl{" +
                "var=" + var +
                ", rhs=" + rhs +
                '}';
    }

    public Toy cpsVal() {
        if(this.rhs instanceof ToyInt) {
            return this.rhs;
        } else if(this.rhs instanceof ToyFunc) {
            //      [(fun x body) (fun k (fun x (split-expr body k)))]))
            ToyFunc func = (ToyFunc) this.rhs;
            List<ToyVar> params = new ArrayList<>(func.getParams());
            params.add(0,CPSUtils.K);
            return new ToyFunc(params, new ToyFunc(func.getParams(), func.getFunction().splitExpression()));
        } else {
            throw new IllegalStateException("This should never happen, You have an invalid test (Or we screwed up)");
        }
    }

    @Override
    public String toJSONString() {
        JSONArray arr = new JSONArray();
        arr.add("let");
        arr.add(this.var);
        arr.add("=");
        arr.add(this.rhs.toJSONString());
        return arr.toJSONString();
    }
}
