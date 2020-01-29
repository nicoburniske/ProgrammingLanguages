package fvexpr;

import answer.Answer;

import java.util.HashMap;

public interface FVExpr {
    public Answer interpret(HashMap<Var, Answer> acc);
}
