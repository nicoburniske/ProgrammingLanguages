package env;

import tast.star_ast.StarAST;
import tpal.TPALVar;
import type.Type;

public class Tuple extends TupleGeneric<StarAST, IEnv<TPALVar, Type>> {
    //<StarAST, IEnv<TPALVar, Type>>
    private StarAST left;
    private IEnv<TPALVar, Type> right;

    public Tuple(StarAST left, IEnv<TPALVar, Type> right) {
        super(left, right);
        this.left = left;
        this.right = right;
    }

    public StarAST getLeft() {
        return left;
    }

    public IEnv<TPALVar, Type> getRight() {
        return right;
    }


}
