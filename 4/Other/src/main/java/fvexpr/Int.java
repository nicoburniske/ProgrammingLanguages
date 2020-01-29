package fvexpr;

import answer.Answer;
import answer.AnswerInt;

import java.math.BigInteger;

public class Int implements FVExpr {
    BigInteger myNum;

    @Override
    public Answer interpret() {
        return new AnswerInt(myNum);
    }
}
