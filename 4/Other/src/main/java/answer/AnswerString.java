package answer;

import static fvexpr.Constants.*;

public class AnswerString extends Answer<String> {
    public AnswerString(String result) {
        super(result);
        if(!result.equals(CLOSURE_STRING)){
            throw new IllegalStateException(result);
        }

    }

    @Override
    public Answer add(Answer obj) {
        if(this.result.equals(CLOSURE_STRING)){
            return new AnswerString(ERROR_INVALID_ARITHMETIC);
        }
        return super.add(obj);
    }

    @Override
    public Answer multiply(Answer obj) {
        if(this.result.equals(CLOSURE_STRING)){
            return new AnswerString(ERROR_INVALID_ARITHMETIC);
        }
        return super.multiply(obj);
    }

    @Override
    public Answer pow(Answer obj) {
        if(this.result.equals(CLOSURE_STRING)){
            return new AnswerString(ERROR_INVALID_ARITHMETIC);
        }
        return super.multiply(obj);
    }
}
