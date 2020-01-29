package fvexpr;

import answer.Answer;
import answer.AnswerString;

import java.util.List;

public class Func implements FVExpr {
    List<Var> aruguments;
    FVExpr function;

    @Override
    public Answer interpret() {
        return new AnswerString( "closure");
    }
}
