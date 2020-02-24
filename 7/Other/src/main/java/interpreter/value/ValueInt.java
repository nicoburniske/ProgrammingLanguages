package interpreter.value;

import java.math.BigInteger;
import java.util.Objects;

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
        return num.toString();
    }

    public BigInteger getNum() {
        return num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueInt valueInt = (ValueInt) o;
        return num.equals(valueInt.num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num);
    }
}
