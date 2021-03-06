package interpreter.pal;

import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.EnvStoreTuple;

/**
 * Represents an untyped XPAL expression that should have been typechecked previously.
 * The RHS of a Decl must be either a PALInt or a PALFunc
 * XPAL is one of:
 * * <ul>
 * *     <li>- a {@link PALVar}</li>
 * *     <li>- an {@link PALInt}</li>
 * *     <li>- a JSON array of the shape [{@link Decl},...,{@link Decl},{@link PAL}]
 * *       all variables declared in one sequence are pairwise distinct</li>
 * *     <li>- a JSON array of the shape ["fun*",VarList,{@link PAL}]</li>
 * *     <li>- a JSON array of the shape ["call",{@link PAL},{@link PAL},...,{@link PAL}]
 * *       as in all mainstream languages, the first and required
 * *       PAL is to evaluate to a function value</li>
 * *     <li>- a JSON array of the shape ["if-0",{@link PAL},{@link PAL},{@link PAL}]</li>
 * *  </ul>
 */
public interface PAL {
    /**
     * ACCUMULATOR STATEMENT: the Tuple that is being accumulated contains our representation of a Store and an Environment,
     * where the Store is appended to with every instantiation of a variable
     * and the Environment is modified to preserve Lexical scoping. This accumulator does not ensure that the function terminates,
     * rather it accepts that it cannot be checked for, and is one of the accepted results of interpreting our program via the Type Soundness Theorem (v3)
     *
     * @return the Tuple of (1) the resulting value yielded from the interpretation of the program and (2) the current Environment and Store Tuple
     */
    ValueEnvStoreTuple interpret(EnvStoreTuple tuple);
}
