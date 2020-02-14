package constants;

/**
 * The constants that contain the Errors that the program can output.
 */
public interface Constants {
    String ERROR_UNDECLARED_VARIABLE_TEMPLATE = "\"variable %s undeclared\"";
    String ERROR_INT_EXPECTED = "\"int expected\"";
    String ERROR_COND_TYPE_ERROR = "\"same type expected for if branches\"";
    String ERROR_FUNCTION_EXPECTED = "\"function type expected\"";
    String ERROR_ARGS_PARAMS_COUNT_DONT_MATCH = "\"number of arguments does not match number of parameters\"";
    String ERROR_ARGS_PARAMS_TYPES_DONT_MATCH = "\"matching function and argument types expected\"";
    String ERROR_DECL_TYPE_MATCHING = "\"matching type declarations and types expected in Decl\"";
}
