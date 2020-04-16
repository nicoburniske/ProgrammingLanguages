package utils.exceptions;

/**
 * An exception for use when a number is expected and instead a different type of value is accepted
 */
public class IntExpectedException extends RuntimeException{
    public IntExpectedException() {
        super("number expected");
    }
}
