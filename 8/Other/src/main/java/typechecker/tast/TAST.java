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
    String toJava(Type type);
    void replaceReservedKeywords(Map<String, String> reserved);
    void replaceReservedKeyword(String varName, String replacement);
}
