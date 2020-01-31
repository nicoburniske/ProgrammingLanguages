package fvexpr;

import answer.Answer;
import answer.AnswerInt;

import java.math.BigInteger;
import java.util.HashMap;

public class Int implements FVExpr {
    BigInteger myNum;

    public Int(Long myNum) {
        this.myNum = new BigInteger(myNum.toString());
    }

    @Override
    public Answer interpret(HashMap<Var, Answer> acc) {
        return new AnswerInt(myNum);
    }
}
