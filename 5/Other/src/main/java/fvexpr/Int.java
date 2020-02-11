package fvexpr;

import answer.Answer;
import answer.AnswerInt;
import org.json.simple.JSONAware;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Int anInt = (Int) o;

        return myNum.equals(anInt.myNum);
    }

    @Override
    public int hashCode() {
        return myNum.hashCode();
    }

    @Override
    public String toJSONString() {
        return this.myNum.toString();
    }
}
