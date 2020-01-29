package fvexpr;

import answer.Answer;

import java.math.BigInteger;

public class Conditional implements FVExpr {
    FVExpr clause;
    FVExpr ifTrue;
    FVExpr ifFalse;

    @Override
    public Answer interpret() {
        if (clause.interpret().equals(new BigInteger("0"))) {
            return ifTrue.interpret();
        } else {
            return ifFalse.interpret();
        }
    }
}
