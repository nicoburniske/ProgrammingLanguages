package typechecker.tpal;

import common.LookupTable;
import interpreter.pal.PAL;
import typechecker.env.Tuple;
import typechecker.type.Type;

/**
 * An FVExpr is one of:
 * <ul>
 *     <li>- a {@link TPALVar}</li>
 *     <li>- an {@link TPALInt}</li>
 *     <li>- a JSON array of the shape [{@link typechecker.tpal.decl.TPALDecl},...,{@link typechecker.tpal.decl.TPALDecl},{@link TPAL}]
 *       all variables declared in one sequence are pairwise distinct</li>
 *     <li>- a JSON array of the shape ["fun*",VarList,{@link TPAL}]</li>
 *     <li>- a JSON array of the shape ["call",{@link TPAL},{@link TPAL},...,{@link TPAL}]
 *       as in all mainstream languages, the first and required
 *       TPAL is to evaluate to a function value</li>
 *     <li>- a JSON array of the shape ["if-0",{@link TPAL},{@link TPAL},{@link TPAL}]</li>
 *  </ul>
 */
public interface TPAL {

    /**
     * This function takes in an enviroment an uses it ot type check the TPAL to confirm if it is a valid exression.
     * @param env the enviroment used to type check the TPAL
     * @return A Tuple which holds a modified envoroment (if changed) and the new StarAST value created.
     */
    Tuple typeCheck(LookupTable<TPALVar, Type> env);

    PAL fillet();
}
