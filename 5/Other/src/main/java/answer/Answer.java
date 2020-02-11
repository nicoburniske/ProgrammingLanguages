package answer;

import fdecl.SFVDecl;
import org.json.simple.JSONAware;

import static fvexpr.Constants.ERROR_INVALID_ARITHMETIC;

/**
 * This class represents the different types of Answers that Interpret can produce.
 * This is where Errors are thrown when there is an Issue
 * @param <T>
 */
public abstract class Answer<T> implements JSONAware {
    public T result;

    public Answer(T result) {
        this.result = result;
    }

    /**
     * Adds two Answers
     * @param obj
     * @return
     */
    public Answer add(Answer obj) {
        return new AnswerString(ERROR_INVALID_ARITHMETIC);
    }

    /**
     * multiples two answers
     * @param obj
     * @return
     */
    public Answer multiply(Answer obj) {
        return new AnswerString(ERROR_INVALID_ARITHMETIC);
    }


    /**
     * Raises this to the power of Object
     * @param obj
     * @return
     */
    public Answer pow(Answer obj) {
        return new AnswerString(ERROR_INVALID_ARITHMETIC);
    }

    public abstract String toJSONString();

}
