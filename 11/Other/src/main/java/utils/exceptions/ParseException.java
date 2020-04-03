package utils.exceptions;

public class ParseException extends RuntimeException {

    public ParseException(String message) {
        super("PARSE EXCEPTION: " + message);
    }

    public static String expectedArray = "Expected an Array";
    public static String expectedStmtBlock = "Expected a stmt Block";
    public static String expectedDecl = "Expected a Decl";
    public static String expectedVar = "Expected Var";
    public static String expectedExpression = "Expected expression";
    public static String expectedStmt = "Expected Stmt";
    public static String expectedLHS = "Expected LHS";
}
