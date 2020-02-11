package answer;

import fvexpr.Func;
import fvexpr.Operator;
import fvexpr.Var;
import store.Location;
import store.Store;

public class AnswerPrimop extends Answer<Func> {
    public Store<Var, Location> env;
    public AnswerPrimop(Func result, Store<Var, Location> env) {
        super(result);
        this.env = new Store<>(env);
    }

    @Override
    public String toJSONString() {
        return "\"primop\"";
    }
}
