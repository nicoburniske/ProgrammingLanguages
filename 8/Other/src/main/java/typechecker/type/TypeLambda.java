package typechecker.type;

import java.util.List;

/**
 * Represents a Lambda containing a Type
 */
public interface TypeLambda extends Type {

    Type call(List<Type> t);

    @Override
    default String toJSONString() {
        return "";
    }

    @Override
    default String toJava() {
        return "";
    }
}
