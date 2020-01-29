package fvexpr;

import answer.Answer;

public class Multiply extends Operator {
    @Override
    public Answer interpret() {
        return left.interpret().multiply(right.interpret());
    }
}
