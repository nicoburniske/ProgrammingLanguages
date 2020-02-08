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
import java.util.Objects;

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
        return new AnswerFunction(this);
        //return new AnswerString(CLOSURE_STRING);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Func func = (Func) o;
        return Objects.equals(parameters, func.parameters) &&
                Objects.equals(function, func.function);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameters, function);
    }

    public Answer apply(List<SFVExpr> arguments, Store<Var, Location> env, Store<Location, Answer> store) {
        if (arguments.size() != this.parameters.size()) {
            return new AnswerString(ERROR_ARGUMENTS_MISMATCH);
        }
        for (int ii = 1; ii <= arguments.size(); ii++) {
            StoreUtils.insertIntoStore(env, store, new SFVDecl(this.parameters.get(arguments.size() - ii), arguments.get(arguments.size() - ii)));
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
}
