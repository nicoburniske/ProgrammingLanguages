package tpal;

import env.IEnv;
import env.Tuple;
import type.TypedVar;
import type.Type;

import java.util.List;
import java.util.Objects;

public class TPALFunc implements TPAL {
    List<TypedVar> parameters;
    TPAL function;

    public TPALFunc(List<TypedVar> parameters, TPAL function) {
        this.parameters = parameters;
        this.function = function;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TPALFunc tpalFunc = (TPALFunc) o;
        return parameters.equals(tpalFunc.parameters) &&
                function.equals(tpalFunc.function);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameters, function);
    }

    @Override
    public String toString() {
        return "TPALFunc{" +
                "parameters=" + parameters +
                ", function=" + function +
                '}';
    }

    @Override
    public Tuple typeCheck(IEnv<TPALVar, Type> env) {
        for(TypedVar param: parameters) {
            env = param.typeCheck(env).getRight();
        }
        return function.typeCheck(env);
    }
}
