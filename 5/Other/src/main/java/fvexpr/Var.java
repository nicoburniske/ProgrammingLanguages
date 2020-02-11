package fvexpr;

import answer.Answer;
import store.Location;
import store.Store;
import store.StoreUtils;

public class Var implements SFVExpr {
    public String myString;

    public Var(String myString) {
        this.myString = myString;
    }

    @Override
    public Answer interpret(Store<Var, Location> env, Store<Location, Answer> store) {
        return StoreUtils.lookup(env, store, this);
    }

    @Override
    public String toJSONString() {
        return String.format("\"%s\"",this.myString);
    }

    @Override
    public String toString() {
        return "\"" + myString + "\"";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Var var = (Var) o;

        return myString.equals(var.myString);
    }

    @Override
    public int hashCode() {
        return myString.hashCode();
    }
}
