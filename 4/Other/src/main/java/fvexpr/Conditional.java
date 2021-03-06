package fvexpr;

import answer.Answer;
import answer.AnswerInt;
import answer.AnswerString;
import org.json.simple.JSONArray;

import java.math.BigInteger;
import java.util.HashMap;

public class Conditional implements FVExpr {
    FVExpr clause;
    FVExpr ifTrue;
    FVExpr ifFalse;

    public Conditional(FVExpr clause, FVExpr ifTrue, FVExpr ifFalse) {
        this.clause = clause;
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;

    }

    @Override
    public Answer interpret(HashMap<Var, Answer> acc) {
        if (clause.interpret(acc).result.equals(new BigInteger("0"))) {
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
