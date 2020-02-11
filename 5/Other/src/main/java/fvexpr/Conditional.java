package fvexpr;

import answer.Answer;
import org.json.simple.JSONArray;
import store.Location;
import store.Store;

import java.math.BigInteger;

public class Conditional implements SFVExpr {
    SFVExpr clause;
    SFVExpr ifTrue;
    SFVExpr ifFalse;

    public Conditional(SFVExpr clause, SFVExpr ifTrue, SFVExpr ifFalse) {
        this.clause = clause;
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;

    }

    @Override
    public Answer interpret(Store<Var, Location> env, Store<Location, Answer> store) {
        if (clause.interpret(env, store).result.equals(new BigInteger("0"))) {
            return ifTrue.interpret(env, store);
        } else {
            return ifFalse.interpret(env, store);
        }
    }


    @Override
    public String toJSONString() {
        JSONArray ret = new JSONArray();
        ret.add("if-0");
        ret.add(clause);
        ret.add(ifTrue);
        ret.add(ifFalse);
        return ret.toJSONString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conditional that = (Conditional) o;

        if (!clause.equals(that.clause)) return false;
        if (!ifTrue.equals(that.ifTrue)) return false;
        return ifFalse.equals(that.ifFalse);
    }

    @Override
    public int hashCode() {
        int result = clause.hashCode();
        result = 31 * result + ifTrue.hashCode();
        result = 31 * result + ifFalse.hashCode();
        return result;
    }
}
