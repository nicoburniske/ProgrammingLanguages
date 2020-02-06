package fvexpr;

import answer.Answer;
import answer.AnswerFunction;
import answer.AnswerString;
import org.json.simple.JSONArray;

import java.util.HashMap;
import java.util.List;

import static fvexpr.Constants.ERROR_CLOSURE_EXPECTED;

public class FuncCall implements SFVExpr {
    SFVExpr func;
    List<SFVExpr> params;

    public FuncCall(SFVExpr func, List<SFVExpr> params) {
        this.func = func;
        this.params = params;
    }


    @Override
    public Answer interpret(HashMap<Var, Answer> acc) {
        if(func instanceof Func) {
            return ((Func)func).apply(params, acc);
        } else if (func instanceof Var && acc.get((Var) func) instanceof AnswerFunction) {
            return ((AnswerFunction)acc.get((Var) func)).result.apply(params,acc);
        }
        else {
            return new AnswerString(ERROR_CLOSURE_EXPECTED);
        }
    }

    @Override
    public String toJson() {
        JSONArray ret = new JSONArray();
        ret.add("call");
        ret.add(func.toJson());
        for (SFVExpr expr: params) {
            ret.add(expr.toJson());
        }
        return ret.toJSONString();
    }
}
