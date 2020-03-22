package interpreter.utils;

/**
 * Constants file for runtime exceptions
 */
public interface RuntimeExceptions {
    String ARITHMETIC_ERROR = "\"primop domain error\"";
    String ERROR_UNDECLARED_VARIABLE_TEMPLATE = "\"variable %s undeclared\"";
    String ERROR_INT_EXPECTED = "\"int expected\"";
    String ERROR_COND_TYPE_ERROR = "\"same type expected for if branches\"";
    String ERROR_FUNCTION_EXPECTED = "\"closure or primop expected\"";
    String ERROR_ARGS_PARAMS_COUNT_DONT_MATCH = "\"number of arguments does not match number of parameters\"";
    String ERROR_ARGS_PARAMS_TYPES_DONT_MATCH = "\"matching function and argument types expected\"";
    String ERROR_DECL_TYPE_MATCHING = "\"matching type declarations and types expected in Decl\"";
}
