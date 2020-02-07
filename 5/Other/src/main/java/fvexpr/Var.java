package fvexpr;

import answer.Answer;
import answer.AnswerString;
import org.json.simple.JSONAware;
import store.Location;
import store.Store;

import static fvexpr.Constants.ERROR_UNDECLARED_VARIABLE_TEMPLATE;

public class Var implements SFVExpr {
    String myString;

    public Var(String myString) {
        this.myString = myString;
    }

    @Override
    public Answer interpret(Store<Var, Location> env, Store<Location, Answer> store) {
        if (env.get(this) != null || store.get(env.get(this)) != null) {
            return store.get(env.get(this));
        } else {
            return new AnswerString(String.format(ERROR_UNDECLARED_VARIABLE_TEMPLATE, this.myString));
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Var var = (Var) o;

        return myString != null ? myString.equals(var.myString) : var.myString == null;
    }

    @Override
    public int hashCode() {
        return myString != null ? myString.hashCode() : 0;
    }

    @Override
    public String toJSONString() {
        return String.format("\"%s\"",this.myString);
    }

    @Override
    public String toString() {
        return "\"" + myString + "\"";
    }
}
