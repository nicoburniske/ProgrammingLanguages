package value;

import java.math.BigInteger;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IValueInt iValueInt = (IValueInt) o;
        return Objects.equals(value, iValueInt.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
