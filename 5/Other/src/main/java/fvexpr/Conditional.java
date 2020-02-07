package fvexpr;

import answer.Answer;
import org.json.simple.JSONArray;
import store.Location;
import store.Store;

import java.math.BigInteger;

public class Conditional implements SFVExpr {
    SFVExpr clause;
    SFVExpr ifTrue;
    SFVExpr ifFalse;

    public Conditional(SFVExpr clause, SFVExpr ifTrue, SFVExpr ifFalse) {
        this.clause = clause;
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;

    }

    @Override
    public Answer interpret(Store<Var, Location> env, Store<Location, Answer> store) {
        if (clause.interpret(env, store).result.equals(new BigInteger("0"))) {
            return ifTrue.interpret(env, store);
        } else {
            return ifFalse.interpret(env, store);
        }
    }


    @Override
    public String toJSONString() {
        JSONArray ret = new JSONArray();
        ret.add("if-0");
        ret.add(clause);
        ret.add(ifTrue);
        ret.add(ifFalse);
        return ret.toJSONString();    }
}
