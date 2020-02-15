package star_decl;

import star_ast.StarAST;
import tast.TASTVar;

/**
 * TODO: DATA Definition
 */
public class StarDecl {
    TASTVar name;
    StarAST rhs;

    public StarDecl(TASTVar name, StarAST rhs) {
        this.name = name;
        this.rhs = rhs;
    }
}
