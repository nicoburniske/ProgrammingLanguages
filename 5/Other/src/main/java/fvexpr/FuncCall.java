package fvexpr;

import answer.Answer;
import answer.AnswerFunction;
import answer.AnswerString;
import org.json.simple.JSONArray;
import store.Location;
import store.Store;
import store.StoreUtils;

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
    public Answer interpret(Store<Var, Location> env, Store<Location, Answer> store) {
        if (func instanceof Func) {
            return ((Func)func).apply(params, env, store);
        } else if (func instanceof Var && StoreUtils.lookup(env, store, (Var)func) instanceof AnswerFunction) {
            return ((AnswerFunction) StoreUtils.lookup(env, store, (Var)func)).result.apply(params, env, store);
        }
        else {
//            System.out.println(store.get(env.get((Var)func)).result.toString());
            return new AnswerString(ERROR_CLOSURE_EXPECTED);
        }
    }


    @Override
    public String toJSONString() {
        JSONArray ret = new JSONArray();
        ret.add("call");
        ret.add(func);
        for (SFVExpr expr: params) {
            ret.add(expr);
        }
        return ret.toJSONString();
    }
}
