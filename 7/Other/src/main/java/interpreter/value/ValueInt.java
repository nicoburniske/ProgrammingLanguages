package interpreter.value;

import java.math.BigInteger;

public class ValueInt implements IValue {
    private BigInteger num;

    public ValueInt(BigInteger num) {
        this.num = num;
    }
    public ValueInt(long num) {
        this.num = new BigInteger(String.valueOf(num));
    }

    @Override
    public String toJSONString() {
        return null;
    }

    public BigInteger getNum() {
        return num;
    }
}
