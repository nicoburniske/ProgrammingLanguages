package ast.stmt;

import org.json.simple.JSONAware;

/**
 * A Stmt is one of:
 *  - [LHS, "=", Expression]           % assignment
 *  - ["if0", Expression, Stmt, Stmt]  % if
 *  - ["do0", Expression, Stmt]        % loop while not 0
 *  - [VarDecl, ..., VarDecl,          % declaration block
 *     "in"                            % set up local variables
 *      Stmt, ..., Stmt,               % execute statements in order
 *      Expression]                    % its value is the result
 */
public interface Stmt extends JSONAware {
    
}
