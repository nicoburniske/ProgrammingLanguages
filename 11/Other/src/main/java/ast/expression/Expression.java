package ast.expression;

import ast.WhileLang;
import org.json.simple.JSONAware;
import utils.EnvStoreTuple;
import utils.env.StaticCheckEnv;
import value.IValue;

/**
 * An Expression is one of:
 *  - Int                            % literal constant
 *  - Var                            % the value of a variable
 *  - [Expression, "+", Expression]  % addition
 *  - [Expression, "*", Expression]  % multiplication
 *  - [Expression, Expression]       % the value of an array index
 */
public interface Expression extends JSONAware, WhileLang {
    Expression staticCheck(StaticCheckEnv env);

    IValue expressionInterpret(EnvStoreTuple tuple);
}
