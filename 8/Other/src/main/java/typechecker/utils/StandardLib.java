package typechecker.utils;

import common.LookupTable;
import common.LookupTableEnd;
import typechecker.tpal.TPALVar;
import typechecker.type.*;

import java.util.Arrays;
import java.util.List;

import static typechecker.utils.Constants.ERROR_ARGS_PARAMS_COUNT_DONT_MATCH;
import static typechecker.utils.Constants.ERROR_ARGS_PARAMS_TYPES_DONT_MATCH;

public class StandardLib {
    /**
     * @return the standard library contaning +,*, and ^
     */
    public static LookupTable<TPALVar, Type> stdLib() {
        LookupTable<TPALVar, Type> env = new LookupTableEnd<TPALVar, Type>();
        Type Int = new TypeInt();
        List<Type> IntInt = Arrays.asList(Int, Int);
        // addition is an (int, int) -> int
        env = env.put(new TPALVar("+"), new TypeFunction(IntInt, Int));
        // multiplication is an (int, int) -> int
        env = env.put(new TPALVar("*"), new TypeFunction(IntInt, Int));
        // exponentiation is an (int, int) -> int
        env = env.put(new TPALVar("^"), new TypeFunction(IntInt, Int));
        // @ is a Type -> [Ref Type]
        env = env.put(new TPALVar("@"), (TypeLambda) (List<Type> types) -> {
            checkLength(types, 1);
            Type type = types.get(0);
            return new TypeFunction(type, new TypeRef(type));
        });
        // ! is a [Ref Type] -> Type
        env = env.put(new TPALVar("!"), (TypeLambda) (List<Type> types) -> {
            checkLength(types, 1);
            if (types.get(0) instanceof TypeRef) {
                TypeRef ref = (TypeRef) types.get(0);
                return new TypeFunction(ref, ref.getType());
            } else {
                throw new IllegalStateException(ERROR_ARGS_PARAMS_TYPES_DONT_MATCH);
            }
        });
        // = is a ( [Ref Type2] Type1 -> Type2 )
        env = env.put(new TPALVar("="), (TypeLambda) (List<Type> types) -> {
            checkLength(types, 2);
            Type left = types.get(0);
            Type right = types.get(1);
            if (left instanceof TypeRef) {
                TypeRef ref = (TypeRef) left;
                return new TypeFunction(Arrays.asList(ref, right), ref.getType());
            } else {
                throw new IllegalStateException(ERROR_ARGS_PARAMS_TYPES_DONT_MATCH);
            }
        });
        return env;
    }

    // used to check the length of the supplied args
    private static void checkLength(List<Type> types, int expectedLen) {
        if (types.size() != expectedLen) {
            throw new IllegalStateException(ERROR_ARGS_PARAMS_COUNT_DONT_MATCH);
        }
    }
}