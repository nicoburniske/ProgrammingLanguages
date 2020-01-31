package fvexpr;

import answer.Answer;
import org.json.simple.JSONArray;

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
