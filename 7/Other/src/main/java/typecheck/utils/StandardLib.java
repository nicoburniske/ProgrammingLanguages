package typecheck.utils;

import typecheck.env.IEnv;
import typecheck.env.IEnvEnd;
import typecheck.tpal.TPALVar;
import typecheck.type.Type;
import typecheck.type.TypeFunction;
import typecheck.type.TypeInt;

import java.util.Arrays;
import java.util.List;

public class StandardLib {
    /**
     *
     * @return the standard library contaning +,*, and ^
     */
    public static IEnv<TPALVar, Type> stdLib() {
        IEnv<TPALVar, Type> env = new IEnvEnd<TPALVar, Type>();
        Type Int = new TypeInt();
        List<Type> IntInt = Arrays.asList(Int,Int);
        env = env.put(new TPALVar("+"), new TypeFunction(IntInt, Int));
        env = env.put(new TPALVar("*"), new TypeFunction(IntInt, Int));
        env = env.put(new TPALVar("^"), new TypeFunction(IntInt, Int));
        return env;
    }
}