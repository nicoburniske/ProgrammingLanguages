package fdecl;

import answer.Answer;
import fvexpr.Func;
import fvexpr.Int;
import fvexpr.Var;
import org.json.simple.JSONArray;

import java.util.HashMap;

public class FDeclInt extends FDecl<Int> {

    public FDeclInt(Var name, Int i) {
        super(name, i);
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
