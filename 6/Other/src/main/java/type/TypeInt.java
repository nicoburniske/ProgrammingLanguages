package type;

import java.math.BigInteger;

public class TypeInt implements Type{
    @Override
    public String toJSONString() {
        return "int";
    }
}
