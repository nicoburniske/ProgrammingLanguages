package fvexpr;

import answer.Answer;
import answer.AnswerString;
import org.json.simple.JSONArray;

import java.util.HashMap;
import java.util.List;

public class Func implements FVExpr {
    List<Var> arguments;
    FVExpr function;

    public Func(List<Var> arguments, FVExpr function) {
        this.arguments = arguments;
        this.function = function;
    }

    @Override
    public Answer interpret(HashMap<Var, Answer> acc) {
        return new AnswerString( "closure");
    }

    @Override
    public String toJson() {
        JSONArray ret = new JSONArray();
        ret.add("fun*");
        ret.add(function.toJson());
        return ret.toJSONString();
    }

    public Answer apply(List<FVExpr> params, HashMap<Var, Answer> acc) {
        if (params.size() != arguments.size()) {
            return new AnswerString("\"number of arguments does not match number of parameters\"");
        }
        HashMap<Var, Answer> envNew = new HashMap<Var, Answer>(acc);
        for(int ii = 0 ; ii < params.size(); ii ++) {
            envNew.put(arguments.get(ii), params.get(ii).interpret(envNew));
        }
        return function.interpret(envNew);
    }
}
