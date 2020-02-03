package fvexpr;

import answer.Answer;
import answer.AnswerFunction;
import org.json.simple.JSONArray;

import java.util.Arrays;
import java.util.HashMap;

public class Operator implements FVExpr {
    FVExpr left;
    FVExpr right;
    Var funcName;

    public Operator(FVExpr left, FVExpr right, Var func) {
        this.left = left;
        this.right = right;
        this.funcName = func;
    }

    @Override
    public Answer interpret(HashMap<Var, Answer> acc){
            return ((AnswerFunction)acc.get(funcName)).result.apply(Arrays.asList(right, left), acc);
    }

    @Override
    public String toJson() {
        JSONArray ret = new JSONArray();
        ret.add(this.left.toJson());
        ret.add(this.funcName.toJson());
        ret.add(this.right.toJson());
        return ret.toJSONString();
    }
}
