package value;

import java.math.BigInteger;

public class IValueInt implements IValue{
    private BigInteger value;

    public IValueInt(int value) {
        this.value = new BigInteger(String.valueOf(value));
    }

    public IValueInt(BigInteger value) {
        this.value = value;
    }

    public BigInteger getValue() {
        return value;
    }
}
