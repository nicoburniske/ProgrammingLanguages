package ast.decl;


import ast.Var;
import ast.WhileLang;
import ast.expression.Expression;
import org.json.simple.JSONAware;
import utils.env.StaticCheckEnv;

import java.util.List;

/**
 * A VarDecl has the shape:
 *  - ["let", Var, "=", Expression]      % declare and initialize variable
 *  - ["vec", Var, "=", Expression,      % declare array and
 *                      .., Expression]  % initial field values
 */
public interface IDecl <T> extends JSONAware, WhileLang{
   IDecl staticCheck(StaticCheckEnv environment);
   Var getVar();
   T getRHS();
}
