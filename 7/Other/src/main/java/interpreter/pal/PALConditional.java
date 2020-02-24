package interpreter.pal;

import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.EnvStoreTuple;
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
        ValueInt cond = (ValueInt) clause.interpret(tuple).getLeft();
        if(cond.getNum().compareTo(new BigInteger("0")) == 0) {
            return ifTrue.interpret(tuple);
        } else {
            return ifFalse.interpret(tuple);
        }
    }
}
