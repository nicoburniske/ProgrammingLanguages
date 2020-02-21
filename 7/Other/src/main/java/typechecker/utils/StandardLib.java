package typechecker.utils;

import common.LookupTable;
import common.LookupTableEnd;
import typechecker.tpal.TPALVar;
import typechecker.type.Type;
import typechecker.type.TypeFunction;
import typechecker.type.TypeInt;

import java.util.Arrays;
import java.util.List;

public class StandardLib {
    /**
     *
     * @return the standard library contaning +,*, and ^
     */
    public static LookupTable<TPALVar, Type> stdLib() {
        LookupTable<TPALVar, Type> env = new LookupTableEnd<TPALVar, Type>();
        Type Int = new TypeInt();
        List<Type> IntInt = Arrays.asList(Int,Int);
        env = env.put(new TPALVar("+"), new TypeFunction(IntInt, Int));
        env = env.put(new TPALVar("*"), new TypeFunction(IntInt, Int));
        env = env.put(new TPALVar("^"), new TypeFunction(IntInt, Int));
        return env;
    }
}