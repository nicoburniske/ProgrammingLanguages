package fvexpr;

import answer.Answer;
import answer.AnswerFunction;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Operator implements FVExpr {
    FVExpr left;
    FVExpr right;
    BinaryFunction funcName;

    public Operator(FVExpr left, FVExpr right, BinaryFunction func) {
        this.left = left;
        this.right = right;
        this.funcName = func;
    }

    @Override
    public Answer interpret(HashMap<Var, Answer> acc){
            return funcName.apply(left.interpret(acc), right.interpret(acc));
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
