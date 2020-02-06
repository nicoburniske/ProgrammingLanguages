package fvexpr;

import answer.Answer;
import store.Store;


/**
 *  An FVExpr is one of:
 *
 *      - a Var
 *
 *      - an Int
 *
 *      - an Operator [FVExpr, Var, FVExpr]
 *
 *      - a DeclArray [FDecl,...,FDecl,FVExpr]
 *
 *      - a Func ["fun*",VarList,FVExpr]
 *
 *      - a FuncCall ["call",FVExpr,FVExpr,...,FVExpr]
 *
 *      - a conditional ["if-0",FVExpr,FVExpr,FVExpr]
 */

public interface SFVExpr {
    /**
      an environment-based interpreter for the language of FVExpr
     * @param env the enviroment that interpret uses to run
     * @return An {@link Answer}
     */
    public Answer interpret(Store<Var, Answer> env);
    String toJson();
}
