package type;

import java.math.BigInteger;

public class TypeInt implements Type{
    BigInteger num;

    public TypeInt(BigInteger num) {
        this.num = num;
    }
}
