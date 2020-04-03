package ast.var_decl;


import org.json.simple.JSONAware;

/**
 * A VarDecl has the shape:
 *  - ["let", Var, "=", Expression]      % declare and initialize variable
 *  - ["vec", Var, "=", Expression,      % declare array and
 *                      .., Expression]  % initial field values
 */
public interface Decl extends JSONAware {
}
