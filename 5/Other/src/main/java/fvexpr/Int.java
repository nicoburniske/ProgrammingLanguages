package fvexpr;

import answer.Answer;
import answer.AnswerInt;
import org.json.simple.JSONAware;
import org.json.simple.JSONValue;
import store.Location;
import store.Store;

import java.math.BigInteger;

public class Int implements SFVExpr, JSONAware {
    BigInteger myNum;

    public Int(Long myNum) {
        this.myNum = new BigInteger(myNum.toString());
    }

    @Override
    public Answer interpret(Store<Var, Location> env, Store<Location, Answer> store) {
        return new AnswerInt(myNum);
    }


    @Override
    public String toJSONString() {
        return this.myNum.toString();
    }
}
