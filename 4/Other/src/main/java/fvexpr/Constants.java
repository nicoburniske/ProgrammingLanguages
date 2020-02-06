package fvexpr;

/**
 * The constants that contain the Errors that the program can outpu
 */
public interface Constants {
    String ERROR_UNDECLARED_VARIABLE_TEMPLATE = "\"variable %s undeclared\"";
    String ERROR_INVALID_ARITHMETIC = "\"arithmetic error\"";
    String ERROR_CLOSURE_EXPECTED = "\"closure or primop expected\"";
    String ERROR_ARGUMENTS_MISMATCH = "\"number of arguments does not match the number of parameters\"";
    String CLOSURE_STRING = "\"closure\"";
}
