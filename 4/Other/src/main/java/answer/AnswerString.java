package answer;

public class AnswerString extends Answer<String> {
    public AnswerString(String result) {
        super(result);
    }

    @Override
    public Answer add(Answer obj) {
        return this;
    }

    @Override
    public Answer multiply(Answer obj) {
        return this;
    }

    @Override
    public Answer pow(Answer obj) {
        return this;
    }
}
