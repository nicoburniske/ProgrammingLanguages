package interpreter.pal;

import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.EnvStoreTuple;
import interpreter.utils.staticDistance.StaticDistanceEnvironment;
import interpreter.utils.staticDistance.TupleSD;
import interpreter.value.IValue;
import interpreter.value.ValueLambdaClosure;
import org.json.simple.JSONArray;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents a sequence of declarations, with a scope
 */
public class ToyDeclArray implements Toy {
    private List<Decl> declList;
    private Toy scope;

    public ToyDeclArray(List<Decl> declList, Toy scope) {
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

    @Override
    public Toy CPS() {
        return new ToyDeclArray(
                this.declList.stream().map(dec -> new Decl(dec.getVar(), dec.cpsVal())).collect(Collectors.toList()),
                scope.CPS());
    }

    @Override
    public Toy splitExpression() {
        //       [(decl x v body) [decl x (cps/value v) (split-expr body k)]]
        return new ToyDeclArray(
                this.declList.stream().map(decl -> new Decl(decl.getVar(), decl.cpsVal())).collect(Collectors.toList()),
                scope.splitExpression());
    }

    @Override
    public Toy computeStaticDistance(int currDepth, StaticDistanceEnvironment env) {
        for (int ii = 0; ii < this.declList.size(); ii++) {
            Decl d = this.declList.get(ii);
            ToyVar var = d.getVar();
            env = env.put(var, new TupleSD(currDepth, ii));
        }

        StaticDistanceEnvironment finalEnv = env;
        List<Decl> declListSD = this.declList.stream().map(d -> {
           return new Decl(new ToyVar("_"), d.getRhs().computeStaticDistance(currDepth + 1, finalEnv));
        }).collect(Collectors.toList());

        return new ToyDeclArray(declListSD, this.scope.computeStaticDistance(currDepth + 2, env));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToyDeclArray that = (ToyDeclArray) o;
        return declList.equals(that.declList) &&
                scope.equals(that.scope);
    }

    @Override
    public int hashCode() {
        return Objects.hash(declList, scope);
    }

    @Override
    public String toString() {
        return "ToyDeclArray{" +
                "declList=" + declList +
                ", scope=" + scope +
                '}';
    }

    @Override
    public String toJSONString() {
        JSONArray arr = new JSONArray();
        arr.addAll(this.declList);
        arr.add(this.scope);
        return arr.toJSONString();
    }
}
