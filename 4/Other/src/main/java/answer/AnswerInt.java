package answer;

import java.math.BigInteger;

public class AnswerInt extends Answer<BigInteger> {
    public AnswerInt(BigInteger myNum) {
        super(myNum);
    }

    @Override
    public Answer add (Answer obj){
        if(obj instanceof AnswerInt) {
            return new AnswerInt(this.result.add(((AnswerInt)obj).result));
        } else {
            return super.add(obj);
        }
    }

    @Override
    public Answer multiply(Answer obj) {
        if(obj instanceof AnswerInt) {
            return new AnswerInt(this.result.multiply(((AnswerInt)obj).result));
        } else {
            return super.multiply(obj);
        }
    }

    @Override
    public Answer pow(Answer obj) {
        if(obj instanceof AnswerInt) {
            return new AnswerInt(this.result.pow(((AnswerInt)obj).result.intValue()));
        } else {
            return super.pow(obj);
        }
    }
}
