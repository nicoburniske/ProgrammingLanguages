package ast.lhs;

import org.json.simple.JSONAware;

/**
 * A LHS is one of:            % LHS stands for lefthand-side
 *  - Var                      % the location of a variable
 *  - [Expression, Expression] % the location of an array index
 */
public interface LHS extends JSONAware {
}
