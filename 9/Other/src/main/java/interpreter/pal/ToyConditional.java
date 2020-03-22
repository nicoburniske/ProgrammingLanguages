package interpreter.pal;

import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.EnvStoreTuple;
import interpreter.utils.staticDistance.StaticDistanceEnvironment;
import interpreter.value.ValueInt;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;


/**
 * Represents a conditional statement
 */
public class ToyConditional implements Toy {
    private Toy clause, ifTrue, ifFalse;

    public ToyConditional(Toy clause, Toy ifTrue, Toy ifFalse) {
        this.clause = clause;
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;
    }

    @Override
    public ValueEnvStoreTuple interpret(EnvStoreTuple tuple) {


        // keep the old environment but use the (possibly) new store
        ValueEnvStoreTuple condTuple = clause.interpret(tuple);
        ValueInt cond = (ValueInt) condTuple.getLeft();
        EnvStoreTuple newTuple = new EnvStoreTuple(tuple.getLeft(), condTuple.getRight().getRight());

        if (cond.getNum().compareTo(new BigInteger("0")) == 0) {
            return ifTrue.interpret(newTuple);
        } else {
            return ifFalse.interpret(newTuple);
        }
    }

    @Override
    public Toy computeStaticDistance(int currDepth, StaticDistanceEnvironment env) {
        return new ToyConditional(this.clause.computeStaticDistance(currDepth, env),
                this.ifTrue.computeStaticDistance(currDepth, env),
                this.ifFalse.computeStaticDistance(currDepth, env));
    }

    @Override
    public Toy splitExpression() {
        return new ToyCall(this.clause.CPS(),new ToyFunc(Arrays.asList(new ToyVar("of-tst")),
                new ToyConditional(new ToyVar("of-tst"), this.ifTrue.splitExpression(), this.ifFalse.splitExpression())));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToyConditional that = (ToyConditional) o;
        return clause.equals(that.clause) &&
                ifTrue.equals(that.ifTrue) &&
                ifFalse.equals(that.ifFalse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clause, ifTrue, ifFalse);
    }

    @Override
    public String toString() {
        return "ToyConditional{" +
                "clause=" + clause +
                ", ifTrue=" + ifTrue +
                ", ifFalse=" + ifFalse +
                '}';
    }
}
