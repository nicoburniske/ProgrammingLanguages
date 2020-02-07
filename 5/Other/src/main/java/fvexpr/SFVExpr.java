package fvexpr;

import answer.Answer;
import org.json.simple.JSONAware;
import store.Location;
import store.Store;

//TODO: create data definition.

public interface SFVExpr extends JSONAware {
    /**
      an environment-based interpreter for the language of FVExpr
     * @param env the enviroment that interpret uses to run
     * @param store
     * @return An {@link Answer}
     */
    public Answer interpret(Store<Var, Location> env, Store<Location, Answer> store);
}
