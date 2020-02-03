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
    public Answer interpret(HashMap<Var, Answer> acc);
    String toJson();
}
