package typechecker.env;

import common.LookupTable;
import common.TupleGeneric;
import typechecker.tast.star_ast.StarAST;
import typechecker.tpal.TPALVar;
import typechecker.type.Type;

/**
 * Specifying which tuple is returned to remove <> from our code
 */
public class Tuple extends TupleGeneric<StarAST, LookupTable<TPALVar, Type>> {

    public Tuple(StarAST left, LookupTable<TPALVar, Type> right) {
        super(left, right);
    }

}
