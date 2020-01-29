package answer;

import java.math.BigInteger;

public class Answer<T> {
    T result;

    public Answer(T result) {
        this.result = result;
    }

    public Answer add (Answer obj) {
        return new AnswerString("\"arithmetic error\"");
    }

    public Answer multiply (Answer obj) {
        return new AnswerString("\"arithmetic error\"");
    }


}
