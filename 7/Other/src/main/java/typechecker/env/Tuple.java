package typechecker.env;

import typechecker.tast.star_ast.StarAST;
import typechecker.tpal.TPALVar;
import typechecker.type.Type;

/**
 * Specifying which tuple is returned to remove <> from our code
 */
public class Tuple extends TupleGeneric<StarAST, IEnv<TPALVar, Type>> {

    public Tuple(StarAST left, IEnv<TPALVar, Type> right) {
        super(left, right);
    }


}
