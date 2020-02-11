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

        if (func.interpret(env, store) instanceof AnswerFunction) {
            AnswerFunction ans = (AnswerFunction) func.interpret(env, store);
            if(func instanceof Var) {
                ans.addMeTOScope((Var)func, env.get((Var)func));
            }
            return ans.result.apply(params, ans.env, store);
        } else {
//            System.out.println(store.get(env.get((Var)func)).result.toString());
            return new AnswerString(ERROR_CLOSURE_EXPECTED);
        }
    }


    @Override
    public String toJSONString() {
        JSONArray ret = new JSONArray();
        ret.add("call");
        ret.add(func);
        for (SFVExpr expr : params) {
            ret.add(expr);
        }
        return ret.toJSONString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FuncCall funcCall = (FuncCall) o;

        if (!func.equals(funcCall.func)) return false;
        return params.equals(funcCall.params);
    }

    @Override
    public int hashCode() {
        int result = func.hashCode();
        result = 31 * result + params.hashCode();
        return result;
    }
}
