package answer;

import fvexpr.Func;
import fvexpr.Var;
import store.Location;
import store.Store;

public class AnswerPrimop extends AnswerFunction {
    public AnswerPrimop(Func result, Store<Var, Location> env) {
        super(result, env);
    }

    @Override
    public String toJSONString() {
        return "\"primop\"";
    }
}
