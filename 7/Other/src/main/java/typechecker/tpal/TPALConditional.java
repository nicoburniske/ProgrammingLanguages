package typechecker.tpal;

import typechecker.env.IEnv;
import typechecker.env.Tuple;
import typechecker.tast.TASTConditional;
import typechecker.tast.star_ast.StarAST;
import typechecker.type.Type;
import typechecker.type.TypeInt;

import java.util.Objects;

import static typechecker.utils.Constants.ERROR_COND_TYPE_ERROR;
import static typechecker.utils.Constants.ERROR_INT_EXPECTED;

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

    @Override
    public Tuple typeCheck(IEnv<TPALVar, Type> env) {
        Tuple ifClaue = clause.typeCheck(env);
         if (!(ifClaue.getLeft().getType() instanceof TypeInt)) {
             throw new IllegalStateException(ERROR_INT_EXPECTED);
         } else {
             Tuple ifTrueTuple = this.ifTrue.typeCheck(env);
             Tuple ifFalseTuple = this.ifFalse.typeCheck(env);
             if(ifTrueTuple.getLeft().getType().equals(ifFalseTuple.getLeft().getType())) {
                 return new Tuple(
                         new StarAST(
                                 new TASTConditional(
                                         ifClaue.getLeft(),
                                         ifTrueTuple.getLeft(),
                                         ifFalseTuple.getLeft()),
                                 ifFalseTuple.getLeft().getType()),
                         env);
             } else {
                 throw new IllegalStateException(ERROR_COND_TYPE_ERROR);
             }
         }
    }
}
