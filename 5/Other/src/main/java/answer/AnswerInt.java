package answer;

import java.math.BigInteger;

import static fvexpr.Constants.ERROR_INVALID_ARITHMETIC;

public class AnswerInt extends Answer<BigInteger> {
    public AnswerInt(BigInteger myNum) {
        super(myNum);
    }

    @Override
    public Answer add(Answer obj) {
        if (obj instanceof AnswerInt) {
            return new AnswerInt(this.result.add(((AnswerInt) obj).result));
        } else {
            return super.add(obj);
        }
    }

    @Override
    public Answer multiply(Answer obj) {
        if (obj instanceof AnswerInt) {
            return new AnswerInt(this.result.multiply(((AnswerInt) obj).result));
        } else {
            return super.multiply(obj);
        }
    }

    @Override
    public Answer pow(Answer obj) {
        if (obj instanceof AnswerInt) {
            if (this.result.intValue() < 0) {
                return new AnswerString(ERROR_INVALID_ARITHMETIC);
            }
            return new AnswerInt(((AnswerInt) obj).result.pow(this.result.intValue()));
        } else {
            return super.pow(obj);
        }
    }

    @Override
    public String toJSONString() {
        return result.toString();
    }

    @Override
    public String toString() {
        return result.toString();
    }
}
