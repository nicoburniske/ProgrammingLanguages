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

public class SFVDecl implements JSONAware {
    public Var name;
    public SFVExpr rhs;

    public SFVDecl(Var name, SFVExpr rhs) {
        this.name = name;
        this.rhs = rhs;
    }

    public Answer interpret(Store<Var, Location> acc, Store<Location, Answer> store) {
        return rhs.interpret(acc, store);
    }



    @Override
    public String toJSONString() {
        JSONArray json = new JSONArray();
        json.add("let");
        json.add(this.name);
        json.add("=");
        json.add(this.rhs);
        return json.toJSONString();    }
}

