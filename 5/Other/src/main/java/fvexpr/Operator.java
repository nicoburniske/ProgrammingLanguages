package fvexpr;

import answer.Answer;
import answer.AnswerFunction;
import org.json.simple.JSONArray;
import store.Location;
import store.Store;
import store.StoreUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Operator implements SFVExpr {
    SFVExpr left;
    List<SFVExpr> rhs;
    Var funcName;

    public Operator(SFVExpr left, List<SFVExpr> right, Var func) {
        this.left = left;
        this.rhs = right;
        this.funcName = func;
    }

    public Operator(SFVExpr left, SFVExpr right, Var func) {
        this.left = left;
        this.rhs = Arrays.asList(right);
        this.funcName = func;
    }

    @Override
    public Answer interpret(Store<Var, Location> env, Store<Location, Answer> store) {
        List<SFVExpr> params = new ArrayList<>();
        params.add(this.left);
        params.addAll(this.rhs);
        return ((AnswerFunction) StoreUtils.lookup(env, store, this.funcName)).result.apply(params, env, store);
    }


    @Override
    public String toJSONString() {
        JSONArray ret = new JSONArray();
        ret.add(left);
        ret.add(this.funcName);
        this.rhs.forEach(arg -> ret.add(arg));
        return ret.toJSONString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Operator operator = (Operator) o;

        if (!left.equals(operator.left)) return false;
        if (!rhs.equals(operator.rhs)) return false;
        return funcName.equals(operator.funcName);
    }

    @Override
    public int hashCode() {
        int result = left.hashCode();
        result = 31 * result + rhs.hashCode();
        result = 31 * result + funcName.hashCode();
        return result;
    }
}
