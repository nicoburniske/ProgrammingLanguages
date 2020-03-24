package interpreter.pal;

import interpreter.utils.CPSUtils;
import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.EnvStoreTuple;
import interpreter.utils.staticDistance.StaticDistanceEnvironment;
import org.json.simple.JSONAware;

import java.util.Arrays;
import java.util.Set;

/**
 * Represents an untyped Toy expression.
 * The RHS of a Decl must be either a ToyInt or a ToyFunc
 * XPAL is one of:
 * * <ul>
 * *     <li>- a {@link ToyVar}</li>
 * *     <li>- an {@link ToyInt}</li>
 * *     <li>- a JSON array of the shape [{@link Decl},...,{@link Decl},{@link Toy}]
 * *       all variables declared in one sequence are pairwise distinct</li>
 * *     <li>- a JSON array of the shape ["fun*",VarList,{@link Toy}]</li>
 * *     <li>- a JSON array of the shape ["call",{@link Toy},{@link Toy},...,{@link Toy}]
 * *       as in all mainstream languages, the first and required
 * *       PAL is to evaluate to a function value</li>
 * *     <li>- a JSON array of the shape ["if-0",{@link Toy},{@link Toy},{@link Toy}]</li>
 * *  </ul>
 */
public interface Toy extends JSONAware {
    /**
     * ACCUMULATOR STATEMENT: the Tuple that is being accumulated contains our representation of a Store and an Environment,
     * where the Store is appended to with every instantiation of a variable
     * and the Environment is modified to preserve Lexical scoping. This accumulator does not ensure that the function terminates,
     * rather it accepts that it cannot be checked for, and is one of the accepted results of interpreting our program via the Type Soundness Theorem (v3)
     *
     * @return the Tuple of (1) the resulting value yielded from the interpretation of the program and (2) the current Environment and Store Tuple
     */
    ValueEnvStoreTuple interpret(EnvStoreTuple tuple);

    /**
     * This function takes a Toy and converts it into static distance form. This means that
     * every variable reference is replaced by the variables distance from its declaration
     * and the variables position in its declaration block {@link interpreter.utils.staticDistance.TupleSD}.
     * If a variable is undeclared it will not be replaced. This function can then be used to help facilitate testing
     *
     * @param currDepth the number of decls that have been passed through to reach this point in the Toy
     * @param env       the environment, which holds the location of declarations of variables for referencing.
     * @return the Toy updated into Static distance form
     */
    Toy computeStaticDistance(int currDepth, StaticDistanceEnvironment env);

    /**
     * This function takes in two {@link Toy} expressions and returns whether or not they are equivalent (ie they
     * have the same structure. variable names may be different, but the expressions should be the same.
     *
     * @param a One {@link Toy} to be evaluated
     * @param b the other {@link Toy} to be evaluated
     * @return whether or not {@param a} and {@param b} are equivalent
     */
    static boolean alphaEquals(Toy a, Toy b) {
        return a.computeStaticDistance(0, new StaticDistanceEnvironment()).equals(b.computeStaticDistance(0, new StaticDistanceEnvironment()));
    }

    /**
     * Converts the given {@link Toy} expression into a continuation passing style expression that when evaluated,
     * will result in the same answer as the original {@link Toy}
     *
     * @return the continuation passing style expression
     */
    default Toy CPS() {
        return new ToyFunc(Arrays.asList(CPSUtils.K), this.splitExpression());
    }

    /**
     * This is a helper Function for CPS. it takes a given Toy expression and converts it into the second half of
     * the continuation passing form.
     *
     * @return a Toy in partial Continuation Passing form
     */
    Toy splitExpression();

    /**
     * obtains all the names in the {@link Decl}s and {@link ToyFunc}'s params.
     *
     * @param names The current set of accumulated names.
     */
    void getAllNames(Set<String> names);
}
