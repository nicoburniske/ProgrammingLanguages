package utils.exceptions;

public class TypeCheckException extends RuntimeException {
    public TypeCheckException(String message) {
        super(message);
    }

    //TODO: number expected??
    public TypeCheckException() {
        super("var undeclared");
    }
}
