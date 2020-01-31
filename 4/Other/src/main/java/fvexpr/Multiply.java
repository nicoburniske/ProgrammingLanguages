package fvexpr;

import answer.Answer;

import java.util.HashMap;

public class Multiply extends Operator {
    public Multiply(FVExpr left, FVExpr right) {
        super(left,right);
    }

    @Override
    public Answer interpret(HashMap<Var, Answer> acc) {
        return right.interpret(acc).multiply(left.interpret(acc));
    }
}
