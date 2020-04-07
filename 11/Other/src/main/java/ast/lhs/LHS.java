package ast.lhs;

import ast.Var;
import ast.WhileLang;
import org.json.simple.JSONAware;
import utils.EnvStoreTuple;
import utils.env.StaticCheckEnv;
import utils.exceptions.ArrayIndexException;
import utils.exceptions.TypeCheckException;
import value.Location;

/**
 * A LHS is one of:            % LHS stands for lefthand-side
 * - Var                      % the location of a variable
 * - [Expression, Expression] % the location of an array index
 */
public interface LHS extends JSONAware, WhileLang {

    /**
     * This function performs a pre evaluation check on the {@link LHS} to ensure that all of the {@link Var}s used
     * are in scope when they are referenced
     * @param environment the current variables in scope at this juncture
     * @return
     * @throws TypeCheckException
     */
    LHS staticCheck(StaticCheckEnv environment) throws TypeCheckException;

    /**
     * This function interprets the {@link LHS} expressions, with the given context of {@param tuple}
     * This function is atomic, it does not modify {@param tuple} rather it only reads from ti
     * @param tuple the evaluation context used to evaluate this {@link LHS}
     * @return the evaluation of this {@link LHS}
     * @throws ArrayIndexException if an array is attempted to be accessed incorrectly
     */
    Location lhsInterpreter(EnvStoreTuple tuple) throws ArrayIndexException;

}
