package utils.exceptions;

/**
 * An exception for when a variable is undeclared, this is used in the static checker.
 */
public class TypeCheckException extends RuntimeException {
    public TypeCheckException() {
        super("var undeclared");
    }
}
