package star_ast;

import tast.TAST;
import type.Type;

/**
 * TODO: add data definition
 */
public class StarAST {
    TAST expr;
    Type type;

    public StarAST(TAST expr, Type type) {
        this.expr = expr;
        this.type = type;
    }
}
