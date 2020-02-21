package interpreter.pal;

import interpreter.value.IValue;
import interpreter.utils.EnvStoreTuple;

public class PALConditional implements PAL {
    private PAL clause, ifTrue, ifFalse;

    public PALConditional(PAL clause, PAL ifTrue, PAL ifFalse) {
        this.clause = clause;
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;
    }

    @Override
    public IValue interpret(EnvStoreTuple tuple) {
        return null;
    }
}
