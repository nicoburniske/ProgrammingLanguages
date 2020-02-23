package typechecker.type;

import java.util.List;

public interface TypeLambda extends Type {

    Type call(List<Type> t);

    @Override
    default String toJSONString() {
        return "";
    }
}
