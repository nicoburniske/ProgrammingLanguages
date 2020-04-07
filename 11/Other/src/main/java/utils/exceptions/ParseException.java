package utils.exceptions;

/**
 * This is an exception used to indicate that there has been an error whilst parsing
 */
public class ParseException extends RuntimeException {

    public ParseException(String message) {
        super(basicMessage);
        //Uncomment this for debugging an issue with parsing
        //super("parser error: " + message);
    }

    //Parser error messages that are helpful for debugging
    public static String basicMessage = "\"parser error\"";
    public static String expectedArray = "Expected an Array";
    public static String expectedStmtBlock = "Expected a stmt Block";
    public static String expectedDecl = "Expected a Decl";
    public static String expectedVar = "Expected Var";
    public static String expectedExpression = "Expected expression";
    public static String expectedStmt = "Expected Stmt";
    public static String expectedLHS = "Expected LHS";
}
