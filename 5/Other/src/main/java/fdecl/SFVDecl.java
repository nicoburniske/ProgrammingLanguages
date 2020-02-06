package fdecl;

import answer.Answer;
import fvexpr.SFVExpr;
import fvexpr.Var;
import org.json.simple.JSONArray;
import store.Location;
import store.Store;

public class SFVDecl {
    public Var name;
    public SFVExpr rhs;

    public SFVDecl(Var name, SFVExpr rhs) {
        this.name = name;
        this.rhs = rhs;
    }

    public Answer interpret(Store<Var, Location> acc, Store<Location, Answer> store) {
        return rhs.interpret(acc, store);
    }

    public String toJson() {
        JSONArray json = new JSONArray();
        json.add("let");
        json.add(this.name.toJson());
        json.add("=");
        json.add(this.rhs.toJson());
        return json.toJSONString();
    }
}

