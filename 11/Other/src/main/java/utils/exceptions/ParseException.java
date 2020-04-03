package utils.exceptions;

public class ParseException extends RuntimeException {

    public ParseException(String message) {
        super("PARSE EXCEPTION: " + message);
    }

    public static String expectedArray = "Expected an Array";
    public static String expectedStmtBlock = "Expected a stmt Block";
}
