package ast.expression;

import org.json.simple.JSONAware;

/**
 * An Expression is one of:
 *  - Int                            % literal constant
 *  - Var                            % the value of a variable
 *  - [Expression, "+", Expression]  % addition
 *  - [Expression, "*", Expression]  % multiplication
 *  - [Expression, Expression]       % the value of an array index
 */
public interface Expression extends JSONAware {
}
