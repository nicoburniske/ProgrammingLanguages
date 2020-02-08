package fvexpr;

import answer.Answer;
import answer.AnswerFunction;
import answer.AnswerString;
import fdecl.SFVDecl;
import org.json.simple.JSONArray;
import store.Location;
import store.Store;
import store.StoreUtils;

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
        return new AnswerFunction(this);
        //return new AnswerString(CLOSURE_STRING);
    }


    public Answer apply(List<SFVExpr> params, Store<Var, Location> env, Store<Location, Answer> store) {
        if (params.size() != arguments.size()) {
            return new AnswerString(ERROR_ARGUMENTS_MISMATCH);
        }
        for (int ii = 1; ii <= params.size(); ii++) {
            StoreUtils.insertIntoStore(env, store, new SFVDecl(arguments.get(params.size() - ii), params.get(params.size() - ii)));
        }
//        System.out.println("=======================\n" + env.toString() + "\n" + store.toString() + "\n=======================");
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
        ret.add(arguments);
        ret.add(function);
        return ret.toJSONString();
    }

    @Override
    public String toString() {
        return "Func{" +
                this.toJSONString() +
                '}';
    }
}
