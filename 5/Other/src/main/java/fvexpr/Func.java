package fvexpr;

import answer.Answer;
import answer.AnswerString;
import org.json.simple.JSONArray;
import store.Location;
import store.Store;

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
    public Answer interpret(Store<Var, Location> env, Store<Location, Answer> store) {
        return new AnswerString(CLOSURE_STRING);
    }


    public Answer apply(List<SFVExpr> params, Store<Var, Location> env, Store<Location, Answer> store) {
        if (params.size() != arguments.size()) {
            return new AnswerString(ERROR_ARGUMENTS_MISMATCH);
        }
        for (int ii = 1; ii <= params.size(); ii++) {
            Location l = new Location(store.getSize());
            env.put(arguments.get(params.size() - ii), l);
            store.put(l, params.get(params.size() - ii).interpret(env, store));
        }
        Answer ans = function.interpret(env, store);
        for (int ii = 1; ii <= params.size(); ii++) {
            env.pop();
        }
        return ans;
    }

    @Override
    public String toJSONString() {
        JSONArray ret = new JSONArray();
        ret.add("fun*");
        ret.add(function);
        return ret.toJSONString();
    }
}
