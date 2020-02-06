package fvexpr;

import answer.Answer;
import answer.AnswerInt;
import org.json.simple.JSONValue;
import store.Store;

import java.math.BigInteger;

public class Int implements SFVExpr {
    BigInteger myNum;

    public Int(Long myNum) {
        this.myNum = new BigInteger(myNum.toString());
    }

    @Override
    public Answer interpret(Store<Var, Answer> env) {
        return new AnswerInt(myNum);
    }

    @Override
    public String toJson() {
        JSONValue ret = new JSONValue();
        return ret.toJSONString(myNum);
    }
}
