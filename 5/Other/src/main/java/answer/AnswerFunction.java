package answer;

import fvexpr.Func;

import static fvexpr.Constants.CLOSURE_STRING;

public class AnswerFunction extends Answer<Func> {
    public AnswerFunction(Func result) {
        super(result);
    }

    @Override
    public String toString() {
        return CLOSURE_STRING;
    }
}
