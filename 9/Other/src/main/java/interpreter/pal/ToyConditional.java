package interpreter.pal;

import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.EnvStoreTuple;
import interpreter.value.ValueInt;

import java.math.BigInteger;

import static interpreter.utils.RuntimeExceptions.ERROR_COND_TYPE_ERROR;
import static interpreter.utils.RuntimeExceptions.ERROR_INT_EXPECTED;

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
        if(! (condTuple.getLeft() instanceof ValueInt)) {
            throw new IllegalStateException(ERROR_INT_EXPECTED);
        }
        ValueInt cond = (ValueInt) condTuple.getLeft();

        EnvStoreTuple newTuple = new EnvStoreTuple(tuple.getLeft(), condTuple.getRight().getRight());
        ValueEnvStoreTuple ifFalseInterpreted = ifFalse.interpret(newTuple);
        ValueEnvStoreTuple ifTrueInterpreted = ifTrue.interpret(newTuple);
        if(!ifFalseInterpreted.getLeft().getClass().equals(ifTrueInterpreted.getLeft().getClass())){
            throw new IllegalStateException(ERROR_COND_TYPE_ERROR);
        }

        if (cond.getNum().compareTo(new BigInteger("0")) == 0) {
            return ifTrueInterpreted;
        } else {
            return ifFalseInterpreted;
        }
    }
}
