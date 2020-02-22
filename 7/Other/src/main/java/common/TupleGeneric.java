package common;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TupleGeneric<?, ?> that = (TupleGeneric<?, ?>) o;
        return left.equals(that.left) &&
                right.equals(that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
