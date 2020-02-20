package typecheck.env;

/**
 * A generic tuple class to help us manage return values
 * @param <Key>
 * @param <Value>
 */
public class TupleGeneric<Key, Value> {
    private Key left;
    private Value right;

    public TupleGeneric(Key left, Value right) {
        this.left = left;
        this.right = right;
    }

    /**
     *
     * @return The left side of the tuple
     */
    public Key getLeft() {
        return left;
    }

    /**
     *
     * @return The right side of the tuple
     */
    public Value getRight() {
        return right;
    }

}
