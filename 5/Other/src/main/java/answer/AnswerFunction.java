package answer;

import fvexpr.Func;
import fvexpr.Var;
import store.Location;
import store.Store;

import static fvexpr.Constants.CLOSURE_STRING;

public class AnswerFunction extends Answer<Func> {
    public Store<Var, Location> env;
    public AnswerFunction(Func result, Store<Var, Location> env) {
        super(result);
        this.env = new Store<>(env);
    }
    public void addMeTOScope(Var name, Location l) {
        env.put(name, l);
    }


    @Override
    public String toJSONString() {
        return "\"closure\"";
    }

    @Override
    public String toString() {
        return CLOSURE_STRING;
    }
}
