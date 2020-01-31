package fvexpr;

import answer.Answer;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;

import java.util.HashMap;

public class Multiply extends Operator {
    public Multiply(FVExpr left, FVExpr right) {
        super(left,right);
    }

    @Override
    public Answer interpret(HashMap<Var, Answer> acc) {
        return right.interpret(acc).multiply(left.interpret(acc));
    }

    @Override
    public String toJson() {
        JSONArray ret = new JSONArray();
        ret.add(this.left.toJson());
        ret.add("*");
        ret.add(this.right.toJson());
        return ret.toJSONString();
    }
}
