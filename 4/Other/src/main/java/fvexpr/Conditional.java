package fvexpr;

import answer.Answer;

import java.math.BigInteger;
import java.util.HashMap;

public class Conditional implements FVExpr {
    FVExpr clause;
    FVExpr ifTrue;
    FVExpr ifFalse;

    @Override
    public Answer interpret(HashMap<Var, Answer> acc) {
        if (clause.interpret(acc).equals(new BigInteger("0"))) {
            return ifTrue.interpret(acc);
        } else {
            return ifFalse.interpret(acc);
        }
    }
}
