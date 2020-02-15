package tpal;

import java.util.Objects;

public class TPALConditional implements TPAL {
    TPAL clause;
    TPAL ifTrue;
    TPAL ifFalse;

    public TPALConditional(TPAL clause, TPAL ifTrue, TPAL ifFalse) {
        this.clause = clause;
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TPALConditional that = (TPALConditional) o;
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
        return "TPALConditional{" +
                "clause=" + clause +
                ", ifTrue=" + ifTrue +
                ", ifFalse=" + ifFalse +
                '}';
    }
}
