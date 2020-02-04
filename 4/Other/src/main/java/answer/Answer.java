package answer;

import static fvexpr.Constants.CLOSURE_STRING;
import static fvexpr.Constants.ERROR_INVALID_ARITHMETIC;

public class Answer<T> {
    public T result;

    public Answer(T result) {
        this.result = result;
    }

    public Answer add(Answer obj) {
        if (obj instanceof AnswerString) {
            if(((AnswerString)obj).result.equals(CLOSURE_STRING)) {
                return new AnswerString(ERROR_INVALID_ARITHMETIC);
            }
            return obj;
        }
        return new AnswerString(ERROR_INVALID_ARITHMETIC);
    }

    public Answer multiply(Answer obj) {
        if (obj instanceof AnswerString) {
            if(((AnswerString)obj).result.equals(CLOSURE_STRING)) {
                return new AnswerString(ERROR_INVALID_ARITHMETIC);
            }
            return obj;
        }
        return new AnswerString(ERROR_INVALID_ARITHMETIC);
    }


    public Answer pow(Answer obj) {
        if (obj instanceof AnswerString) {
            if(((AnswerString)obj).result.equals(CLOSURE_STRING)) {
                return new AnswerString(ERROR_INVALID_ARITHMETIC);
            }
            return obj;
        }
        return new AnswerString(ERROR_INVALID_ARITHMETIC);
    }

}
