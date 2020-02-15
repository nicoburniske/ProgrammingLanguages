package tast;

import java.math.BigInteger;

public class TASTInteger implements TAST {
    BigInteger i;

    public TASTInteger(BigInteger i) {
        this.i = i;
    }

    @Override
    public String toJSONString() {
        return i.toString();
    }
}
