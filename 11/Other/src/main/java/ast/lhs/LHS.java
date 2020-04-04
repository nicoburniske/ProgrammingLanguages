package ast.lhs;

import ast.WhileLang;
import org.json.simple.JSONAware;
import utils.env.Environment;

/**
 * A LHS is one of:            % LHS stands for lefthand-side
 *  - Var                      % the location of a variable
 *  - [Expression, Expression] % the location of an array index
 */
public interface LHS extends JSONAware, WhileLang {
    LHS typecheck(Environment environment);
}
