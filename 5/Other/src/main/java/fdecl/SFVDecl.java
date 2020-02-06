package fdecl;

import answer.Answer;
import fvexpr.SFVExpr;
import fvexpr.Var;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import store.Store;

import java.util.HashMap;

public class SFVDecl {
    public Var name;
    public SFVExpr rhs;

    public SFVDecl(Var name, SFVExpr rhs) {
        this.name = name;
        this.rhs = rhs;
    }

    public Answer interpret(Store<Var, Answer> acc) {
        rhs.interpret(acc);
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

