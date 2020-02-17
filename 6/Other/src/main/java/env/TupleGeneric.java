package env;

import tast.star_ast.StarAST;
import tpal.TPALVar;
import type.Type;

public class TupleGeneric<Key, Value> {
    private Key left;
    private Value right;

    public TupleGeneric(Key left, Value right) {
        this.left = left;
        this.right = right;
    }

    public Key getLeft() {
        return left;
    }

    public Value getRight() {
        return right;
    }

}
