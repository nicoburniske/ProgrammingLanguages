package fvexpr;

import answer.Answer;
import answer.AnswerFunction;
import answer.AnswerString;

import java.util.HashMap;
import java.util.List;

public class FuncCall implements FVExpr{
    FVExpr func;
    List<FVExpr> params;


    @Override
    public Answer interpret(HashMap<Var, Answer> acc) {
        if(func instanceof Func) {
            return ((Func)func).apply(params, acc);
        } else if (func instanceof Var && acc.get((Var) func) instanceof AnswerFunction) {
            return ((AnswerFunction)acc.get((Var) func)).result.apply(params,acc);
        }
        else {
            return new AnswerString("\"function application (closure expected)\"");
        }
    }
}
