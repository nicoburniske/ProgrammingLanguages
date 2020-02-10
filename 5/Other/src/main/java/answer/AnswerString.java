package answer;

import static fvexpr.Constants.CLOSURE_STRING;


public class AnswerString extends Answer<String> {
    public AnswerString(String result) {
        super(result);
        if(!result.equals(CLOSURE_STRING)){
            throw new IllegalStateException(result);
        }

    }

    @Override
    public Answer add(Answer obj) {
        return super.add(obj);
    }

    @Override
    public Answer multiply(Answer obj) {
        return super.multiply(obj);
    }

    @Override
    public Answer pow(Answer obj) {
        return super.multiply(obj);
    }

    @Override
    public String toJSONString() {
        return result;
    }

    @Override
    public String toString() {
        return result;
    }
}
