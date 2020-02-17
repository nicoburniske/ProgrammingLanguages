package tast;

import java.math.BigInteger;
import java.util.Objects;

public class TASTInteger implements TAST {
    BigInteger i;

    public TASTInteger(BigInteger i) {
        this.i = i;
    }

    @Override
    public String toJSONString() {
        return i.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TASTInteger that = (TASTInteger) o;
        return i.equals(that.i);
    }

    @Override
    public int hashCode() {
        return Objects.hash(i);
    }
}
