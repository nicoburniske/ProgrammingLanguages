package typechecker.tast;

import org.json.simple.JSONAware;
import typechecker.type.Type;

import java.util.Map;

/**
 * A TAST expression has one of these shapes:
 * <ul>
 *     <li>- a Var</li>
 *     <li>- an Int</li>
 *     <li>
 *         - a JSON array of the shape [*Decl,...,*Decl,*AST]
 *         all variables declared in one sequence are pairwise distinct
 *     </li>
 *     <li>
 *          - a JSON array of the shape ["fun*",[TVar, ..., TVar] ,*AST]
 *          all parameters in a sequence are pairwise distinct
 *     </li>
 *     <li>
 *          - a JSON array of the shape ["call",*AST,*AST,...,*AST]
 *          as in all mainstream languages, the first and required
 *          *AST is to evaluate to a function value
 *     </li>
 *     <li>- a JSON array of the shape ["if-0",*AST,*AST,*AST]</li>
 * </ul>
 */
public interface TAST extends JSONAware {

    /**
     * This function creates a Java Program that matches the TAST.
     * It takes in its (Java) return type so that it know what (java)
     * Type it should be returning
     * @param type the (Java) return type of this
     * @return a Java Program (represented as a string) that corresponds to this TAST
     */
    String toJava(Type type);

    /**
     * This function itterates though the TAST and replaces and Decls that
     * use a reserved key such as !, @, + and replaces. that var with a new String
     * @param reserved the map of keywords to their replacements.
     */
    void replaceReservedKeywords(Map<String, String> reserved);

    /**
     * This function replaces a specified Keyword within a scope with its replacement
     * @param varName the var to be replaced
     * @param replacement the replacement var
     */
    void replaceReservedKeyword(String varName, String replacement);
}
