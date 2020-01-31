package fdecl;

import answer.Answer;
import fvexpr.Func;
import fvexpr.Var;
import org.json.simple.JSONArray;

import java.util.HashMap;

public class FDeclFVExpr extends FDecl<Func> {

    public FDeclFVExpr(Var name, Func f) {
        super(name, f);
    }
    @Override
    public Answer interpret(HashMap<Var, Answer> acc) {
        return rhs.interpret(acc);
    }

    @Override
    public String toJson() {
        JSONArray ret = new JSONArray();
        ret.add("let");
        ret.add(super.name.toJson());
        ret.add(super.rhs.toJson());
        return ret.toJSONString();
    }
}
