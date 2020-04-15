package utils.exceptions;

/**
 * This is the exception that is thrown when the store runs out memory.
 */
public class StoreSizeException extends RuntimeException {
    public StoreSizeException() {
        super("out of space");
    }
}
