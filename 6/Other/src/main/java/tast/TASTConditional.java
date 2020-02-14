package tast;

import star_ast.StarAST;

public class TASTConditional implements TAST {
    StarAST condClause;
    StarAST ifTrue;
    StarAST ifFalse;

    public TASTConditional(StarAST condClause, StarAST ifTrue, StarAST ifFalse) {
        this.condClause = condClause;
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;
    }
}
