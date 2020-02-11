package fvexpr;

import answer.Answer;
import answer.AnswerFunction;
import answer.AnswerString;
import fdecl.SFVDecl;
import org.json.simple.JSONArray;
import store.Location;
import store.Store;
import store.StoreUtils;

import java.util.ArrayList;
import java.util.List;

import static fvexpr.Constants.ERROR_ARGUMENTS_MISMATCH;

public class Func implements SFVExpr {
    List<Var> parameters;
    SFVExpr function;

    public Func(List<Var> parameters, SFVExpr function) {
        this.parameters = parameters;
        this.function = function;
    }

    @Override
    public Answer interpret(Store<Var, Location> env, Store<Location, Answer> store) {
        return new AnswerFunction(this, env);
        //return new AnswerString(CLOSURE_STRING);
    }

    public Answer apply(List<SFVExpr> arguments, Store<Var, Location> env, Store<Location, Answer> store) {
        if (arguments.size() != this.parameters.size()) {
            return new AnswerString(ERROR_ARGUMENTS_MISMATCH);
        }
        List<Answer> interpretedArgs = new ArrayList<>();

        for (int ii = 1; ii <= arguments.size(); ii++) {
            interpretedArgs.add(arguments.get(arguments.size() - ii).interpret(env, store));
        }

        for (int ii = 1; ii <= arguments.size(); ii++) {
            StoreUtils.insertIntoStore(env, store, this.parameters.get(arguments.size() - ii), interpretedArgs.get(ii - 1));
        }
//        System.out.println("=======================\n" + env.toString() + "\n" + store.toString() + "\n=======================");
        Answer ans = function.interpret(env, store);

        for (SFVExpr v : arguments) {
            env.pop();
        }

        return ans;
    }

    @Override
    public String toJSONString() {
        JSONArray ret = new JSONArray();
        ret.add("fun*");
        ret.add(parameters);
        ret.add(function);
        return ret.toJSONString();
    }

    @Override
    public String toString() {
        return "Func{" +
                this.toJSONString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Func func = (Func) o;

        if (!parameters.equals(func.parameters)) return false;
        return function.equals(func.function);
    }

    @Override
    public int hashCode() {
        int result = parameters.hashCode();
        result = 31 * result + function.hashCode();
        return result;
    }
}
