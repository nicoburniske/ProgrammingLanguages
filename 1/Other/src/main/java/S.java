/**
 * An S is one of:
 * - an SName
 * - an SArray where each element is an S.
 */
public interface S {
    /**
     * Determines how many SNames are in the given S.
     *
     * @return the number of SNames.
     */
    public int count();

    /**
     * Constructs a new S, where every SName is replaced with it's dual.
     *
     * @return a new S.
     */
    public S replace();

    /**
     * Constructs a new T with the same array shape as the given S, where
     * every SName is replaced with a TInteger with the appropriate depth.
     *
     * @return a new T.
     */
    public T context();

    /**
     * Constructs a new T with the same array shape as the given S, where
     * every SName is replaced with a TInteger with the appropriate depth.
     *
     * @param depth the starting depth.
     * @return a new T.
     */
    public T context(int depth);

    /**
     * Converts the given S to a JSON.
     *
     * @return JSON as a String.
     */
    public String toJSON();
}
