package utils;

import env.IEnv;
import env.IEnvEnd;
import tpal.TPALVar;
import type.Type;
import type.TypeFunction;
import type.TypeInt;

import java.util.Arrays;
import java.util.List;

public class StandardLib {
    public static IEnv<TPALVar, Type> stdLib() {
        IEnv<TPALVar, Type> env = new IEnvEnd<TPALVar, Type>();
        Type Int = new TypeInt();
        List<Type> IntInt = Arrays.asList(Int,Int);
        env = env.put(new TPALVar("+"), new TypeFunction(IntInt, Int));
        env = env.put(new TPALVar("-"), new TypeFunction(IntInt, Int));
        env = env.put(new TPALVar("^"), new TypeFunction(IntInt, Int));
        return env;
    }
}