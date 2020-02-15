package tpal;

public class TPALConditional implements TPAL {
    TPAL clause;
    TPAL ifTrue;
    TPAL ifFalse;

    public TPALConditional(TPAL clause, TPAL ifTrue, TPAL ifFalse) {
        this.clause = clause;
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;
    }
}
