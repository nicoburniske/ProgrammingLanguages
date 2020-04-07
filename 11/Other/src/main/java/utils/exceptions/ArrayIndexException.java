package utils.exceptions;

/**
 * A exception that will be thrown when there is an indexing error
 * 1)[[1], 5]     Array index out of bounds
 * 2)[[1,2,3],[]] index is not a number
 * 3)[2,2]        array for access is not an array
 */
public class ArrayIndexException extends RuntimeException {
    public ArrayIndexException() {
        super("indexing error");
    }
}
