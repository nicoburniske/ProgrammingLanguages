package fvexpr;

import answer.Answer;
import answer.AnswerFunction;
import answer.AnswerString;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static fvexpr.Constants.ERROR_CLOSURE_EXPECTED;

public class Operator implements SFVExpr {
    SFVExpr left;
    List<SFVExpr> rhs;
    Var funcName;

    public Operator(SFVExpr left, List<SFVExpr> right, Var func) {
        this.left = left;
        this.rhs = right;
        this.funcName = func;
    }
    @Override
    public Answer interpret(HashMap<Var, Answer> acc) {
        if (acc.get(funcName) != null) {
            List<SFVExpr> params = new ArrayList<>();
            params.add(this.left);
            params.addAll(this.rhs);
            return ((AnswerFunction) this.funcName.interpret(acc)).result.apply( params, acc);
        } else {
            return new AnswerString(ERROR_CLOSURE_EXPECTED);
        }
    }

    @Override
    public String toJson() {
        JSONArray ret = new JSONArray();
       //TODO fix tojson
        return ret.toJSONString();
    }
}
