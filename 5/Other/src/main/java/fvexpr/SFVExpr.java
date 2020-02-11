package fvexpr;

import answer.Answer;
import org.json.simple.JSONAware;
import store.Location;
import store.Store;

/**
 * An FVExpr is one of:
 * <ul>
 *     <li>- a {@link Var}</li>
 *     <li>- an {@link Int}</li>
 *     <li>- a JSON array of the shape [{@link SFVExpr},"+",{@link SFVExpr}]</li>
 *     <li>- a JSON array of the shape [{@link SFVExpr},"*",{@link SFVExpr}]</li>
 *     <li>- a JSON array of the shape [{@link fdecl.SFVDecl},...,{@link fdecl.SFVDecl},{@link SFVExpr}]
 *       all variables declared in one sequence are pairwise distinct</li>
 *     <li>- a JSON array of the shape ["fun*",VarList,{@link SFVExpr}]</li>
 *     <li>- a JSON array of the shape ["call",{@link SFVExpr},{@link SFVExpr},...,{@link SFVExpr}]
 *       as in all mainstream languages, the first and required
 *       FVExpr is to evaluate to a function value</li>
 *     <li>- a JSON array of the shape ["if-0",{@link SFVExpr},{@link SFVExpr},{@link SFVExpr}]</li>
 *     <li>- a JSON array of the shape [{@link SFVExpr},Var,{@link SFVExpr}, ..., {@link SFVExpr}] where the
 *      first {@link SFVExpr} is not a keyword</li>
 *     <li>- a JSON array of the shape [{@link fdecl.SFVDecl},...,{@link fdecl.SFVDecl},{@link SFVExpr}],
 *       all variables declared in one sequence are pairwise distinct</li>
 *  </ul>
 */
public interface SFVExpr extends JSONAware {
    /**
     * Takes an SFVExper and interprets it using the {@param env} and the {@param store}.
     * an environment-store-based interpreter for the language of {@link SFVExpr}
     *
     * @param env   the enviroment that is used to lookup and store the {@link Location}s of {@link Var}s
     * @param store the store that that is used to lookup the {@link Answer}s of {@link Location}s
     * @return An {@link Answer}
     */
    public Answer interpret(Store<Var, Location> env, Store<Location, Answer> store);


    /**
     * This functions converts {@link SFVExpr} into JSON for
     * printing using the {@link JSONAware} library
     *
     * @return A JSON formatted String
     */
    @Override
    String toJSONString();
}
