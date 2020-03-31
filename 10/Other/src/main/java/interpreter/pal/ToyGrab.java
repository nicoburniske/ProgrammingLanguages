package interpreter.pal;

import interpreter.utils.CPSUtils;
import interpreter.utils.EnvStoreTuple;
import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.staticDistance.StaticDistanceEnvironment;
import interpreter.utils.staticDistance.TupleSD;
import org.json.simple.JSONArray;

import java.util.*;

/**
 * This sructure represents a try catch expression. which turns the continuation of the grab expression into a closure,
 * binds it to the specified Var, and evaluates the “body” expression to obtain a value.
 *
 * This should never be interpreted as Grab will be transformed in .CPS()
 */
public class ToyGrab implements Toy {
    ToyVar var;
    Toy rhs;

    public ToyGrab(ToyVar var, Toy rhs) {
        this.var = var;
        this.rhs = rhs;
    }

    @Override
    public ValueEnvStoreTuple interpret(EnvStoreTuple tuple) {
        throw new IllegalStateException("you should not be interpreting on Grab");
    }

    @Override
    public Toy computeStaticDistance(int currDepth, StaticDistanceEnvironment env) {
        return new ToyGrab(this.var, this.rhs.computeStaticDistance(currDepth + 1,env.put(this.var, new TupleSD(currDepth, 0)) ));
    }

    @Override
    public Toy splitExpression() {
        // ToyVar param = new ToyVar(CPSUtils.nameGenerator());
        ToyVar param2 = new ToyVar(CPSUtils.nameGenerator());
        Decl done = new Decl(this.var, new ToyFunc(Arrays.asList(param2, this.var), new ToyCall(CPSUtils.K, Arrays.asList(this.var))));
        return new ToyDeclArray(Arrays.asList(done), this.rhs.splitExpression());
    }

    @Override
    public void getAllNames(Set<String> names) {
        names.add(this.var.toString());
        this.rhs.getAllNames(names);
    }

    @Override
    public String toJSONString() {
        JSONArray arr = new JSONArray();
        arr.add("grab");
        arr.add(this.var);
        arr.add(this.rhs);
        return arr.toJSONString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToyGrab toyGrab = (ToyGrab) o;
        return var.equals(toyGrab.var) &&
                rhs.equals(toyGrab.rhs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(var, rhs);
    }
}
