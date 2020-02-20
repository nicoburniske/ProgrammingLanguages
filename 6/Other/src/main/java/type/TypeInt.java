package type;

import java.math.BigInteger;

public class TypeInt implements Type{
    @Override
    public String toJSONString() {
        return "\"int\"";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof TypeInt;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
