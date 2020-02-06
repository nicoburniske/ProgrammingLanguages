package fvexpr;

import answer.Answer;
import org.json.simple.JSONArray;
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
    public Answer interpret(Store<Var, Answer> env) {
        if (clause.interpret(env).result.equals(new BigInteger("0"))) {
            return ifTrue.interpret(env);
        } else {
            return ifFalse.interpret(env);
        }
    }

    @Override
    public String toJson() {
        JSONArray ret = new JSONArray();
        ret.add("if-0");
        ret.add(clause.toJson());
        ret.add(ifTrue.toJson());
        ret.add(ifFalse.toJson());
        return ret.toJSONString();
    }


}
