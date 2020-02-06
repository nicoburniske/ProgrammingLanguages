package fvexpr;

import answer.Answer;
import answer.AnswerString;
import org.json.simple.JSONArray;
import store.Store;

import java.util.HashMap;
import java.util.List;

import static fvexpr.Constants.CLOSURE_STRING;
import static fvexpr.Constants.ERROR_ARGUMENTS_MISMATCH;

public class Func implements SFVExpr {
    List<Var> arguments;
    SFVExpr function;

    public Func(List<Var> arguments, SFVExpr function) {
        this.arguments = arguments;
        this.function = function;
    }

    @Override
    public Answer interpret(Store<Var, Answer> env) {
        return new AnswerString(CLOSURE_STRING);
    }

    @Override
    public String toJson() {
        JSONArray ret = new JSONArray();
        ret.add("fun*");
        ret.add(function.toJson());
        return ret.toJSONString();
    }

    public Answer apply(List<SFVExpr> params, Store<Var, Answer> acc) {
        if (params.size() != arguments.size()) {
            return new AnswerString(ERROR_ARGUMENTS_MISMATCH);
        }
        for (int ii = 1; ii <= params.size(); ii++) {
            acc.put(arguments.get(params.size() - ii), params.get(params.size() - ii).interpret(acc));
        }
        Answer ans = function.interpret(acc);
        for (int ii = 1; ii <= params.size(); ii++) {
            acc.pop();
        }
        return ans;
    }
}
