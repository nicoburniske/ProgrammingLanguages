package fvexpr;

import answer.Answer;
import answer.AnswerInt;

public class Plus extends Operator {
    @Override
    public Answer interpret() {
        return left.interpret().add(right.interpret());
    }
}
