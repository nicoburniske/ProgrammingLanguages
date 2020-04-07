package ast.expression;

import ast.Var;
import ast.WhileLang;
import org.json.simple.JSONAware;
import utils.EnvStoreTuple;
import utils.env.StaticCheckEnv;
import utils.exceptions.TypeCheckException;
import value.IValue;

/**
 * An Expression is one of:
 * - Int                            % literal constant
 * - Var                            % the value of a variable
 * - [Expression, "+", Expression]  % addition
 * - [Expression, "*", Expression]  % multiplication
 * - [Expression, Expression]       % the value of an array index
 */
public interface Expression extends JSONAware, WhileLang {

    /**
     * THis functions performs a pre evaluation check, that ensures that al variables that are used have
     * been declared in the scope
     *
     * @param env the {@link Var}s currently in scope
     * @return the Expression
     * @throws TypeCheckException if there is an undeclared variable in the expression.
     */
    Expression staticCheck(StaticCheckEnv env) throws TypeCheckException;

    /**
     * This function interprets the {@link Expression} with the context of the {@param tuple}.
     * This is an atomic operation and so does not modify the {@param tuple}, it will only read from it
     *
     * @param tuple the current evaluation context
     * @return the {@link IValue} that the expression evaluates to
     */
    IValue expressionInterpret(EnvStoreTuple tuple);
}
