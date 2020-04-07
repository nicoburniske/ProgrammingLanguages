package ast.stmt;

import ast.Var;
import ast.WhileLang;
import org.json.simple.JSONAware;
import utils.EnvStoreTuple;
import utils.env.StaticCheckEnv;
import utils.exceptions.TypeCheckException;
import utils.store.Store;


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
public interface Stmt extends JSONAware, WhileLang {

   /**
    * This function runs pre-processing on a {@link Stmt} to ensure that all variables that are used are declared.
    *
    * @param env the currently declared variables in this scope
    * @return the {@link Stmt} copied if there are no undeclared uses of a {@link Var}
    * @throws TypeCheckException if there is an undeclared variable used in the {@link Stmt}
    */
   Stmt staticCheck(StaticCheckEnv env) throws TypeCheckException;

   /**
    * this function performs the transition table for the {@link Stmt}
    * @param tuple the current evaluation context
    * @return the store, updated by thr {@link Stmt}
    */
   Store transition(EnvStoreTuple tuple);
}
