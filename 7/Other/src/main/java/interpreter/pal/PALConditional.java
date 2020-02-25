package interpreter.pal;

import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.EnvStoreTuple;
import interpreter.value.IValue;
import interpreter.value.ValueInt;

import java.math.BigInteger;

public class PALConditional implements PAL {
    private PAL clause, ifTrue, ifFalse;

    public PALConditional(PAL clause, PAL ifTrue, PAL ifFalse) {
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
}
