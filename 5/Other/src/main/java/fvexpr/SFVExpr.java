package fvexpr;

import answer.Answer;
import store.Store;

//TODO: create data definition.

public interface SFVExpr {
    /**
      an environment-based interpreter for the language of FVExpr
     * @param env the enviroment that interpret uses to run
     * @return An {@link Answer}
     */
    public Answer interpret(Store<Var, Answer> env);
    String toJson();
}
