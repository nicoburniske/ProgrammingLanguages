package answer;

import fvexpr.Operator;

public class AnswerPrimop extends Answer<Operator> {
    public AnswerPrimop(Operator result) {
        super(result);
    }

    @Override
    public String toJSONString() {
        return "\"primop\"";
    }
}
