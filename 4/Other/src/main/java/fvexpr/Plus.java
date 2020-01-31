package fvexpr;

import answer.Answer;

import java.util.HashMap;

public class Plus extends Operator {
    public Plus(FVExpr left, FVExpr right) {
        super(left, right);
    }

    @Override
    public Answer interpret(HashMap<Var, Answer> acc) {
        return right.interpret(acc).add(left.interpret(acc));
    }
}
