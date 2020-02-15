package tpal;

import type.TVar;

import java.util.List;
import java.util.Objects;

public class TPALFunc implements TPAL {
    List<TVar> parameters;
    TPAL function;

    public TPALFunc(List<TVar> parameters, TPAL function) {
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
}
