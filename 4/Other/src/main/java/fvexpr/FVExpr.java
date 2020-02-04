package fvexpr;

import answer.Answer;

import java.util.HashMap;


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

public interface FVExpr {
    /**
      an environment-based interpreter for the language of FVExpr
     * @param acc the enviroment that interpret uses to run
     * @return An {@link Answer}
     */
    public Answer interpret(HashMap<Var, Answer> acc);
    String toJson();
}
