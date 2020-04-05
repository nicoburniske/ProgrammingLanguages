package ast.stmt;

import ast.WhileLang;
import org.json.simple.JSONAware;
import utils.EnvStoreTuple;
import utils.env.StaticCheckEnv;
import utils.store.Store;
import value.IValue;
import value.IValueInt;
import value.Location;

import java.util.Stack;
import java.util.function.Function;

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
   Stmt staticCheck(StaticCheckEnv env);
   Store transition(EnvStoreTuple tuple);
}
