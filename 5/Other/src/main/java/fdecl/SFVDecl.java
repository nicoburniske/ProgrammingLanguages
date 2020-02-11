package fdecl;

import answer.Answer;
import answer.AnswerFunction;
import fvexpr.Func;
import fvexpr.SFVExpr;
import fvexpr.Var;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import store.Location;
import store.Store;
import store.StoreUtils;

/**
 *  Represents a Declaration of a SFVExpr to a Variable
 */
public class SFVDecl implements JSONAware {
    public Var name;
    public SFVExpr rhs;

    public SFVDecl(Var name, SFVExpr rhs) {
        this.name = name;
        this.rhs = rhs;
    }

    /**
     * Interprets the SFVExpr value (rhs) of the declaration.
     * @param env the current environment
     * @param store the current store
     * @return The answer representing the interpreted value of the rhs
     */
    public Answer interpret(Store<Var, Location> env, Store<Location, Answer> store) {
        return rhs.interpret(env, store);
    }

    /**
     * This functions converts {@link SFVDecl} into JSON for
     * printing using the {@link JSONAware} interface from our JSON Parsing library
     * @return A JSON formatted String
     */
    @Override
    public String toJSONString() {
        JSONArray json = new JSONArray();
        json.add("let");
        json.add(this.name);
        json.add("=");
        json.add(this.rhs);
        return json.toJSONString();    }
}

