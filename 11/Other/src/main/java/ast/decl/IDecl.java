package ast.decl;


import ast.Var;
import ast.WhileLang;
import ast.expression.Expression;
import org.json.simple.JSONAware;
import utils.env.StaticCheckEnv;
import utils.exceptions.TypeCheckException;

import java.util.List;

/**
 * A VarDecl has the shape:
 * - ["let", Var, "=", Expression]      % declare and initialize variable
 * - ["vec", Var, "=", Expression,      % declare array and
 * .., Expression]  % initial field values
 */
public interface IDecl<T> extends JSONAware, WhileLang {
    /**
     * This functio performs a pre-evaluation check to ensure that all of the {@link Var} have been declared
     *
     * @throws TypeCheckException if the decl does not typecheck
     * @param environment the current enviroment containing all of th declared variables in scope.
     * @return the Same {@link IDecl}
     */
    IDecl staticCheck(StaticCheckEnv environment) throws TypeCheckException;

   /**
    * This function returns the Var of the Decl
    */
    Var getVar();

   /**
    * This function returns the RHS of the Decl
    */
    T getRHS();
}
