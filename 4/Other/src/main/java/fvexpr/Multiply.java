package fvexpr;

import answer.Answer;

public class Multiply extends Operator {
    @Override
    public Answer interpret() {
        return right.interpret().multiply(left.interpret());
    }
}
