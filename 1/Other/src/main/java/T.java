/**
 * A T is one of:
 * - a TInteger
 * - a TArray where each element is a T.
 */
public interface T {
    /**
     * Converts the given T to a JSON.
     *
     * @return JSON as a String.
     */
    public String toJSON();
}