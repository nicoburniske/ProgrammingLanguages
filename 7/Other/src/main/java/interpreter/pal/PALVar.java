package interpreter.pal;

import interpreter.value.IValue;
import interpreter.utils.EnvStoreTuple;

import java.util.Objects;

public class PALVar implements PAL {
    private String var;

    public PALVar(String var) {
        this.var = var;
    }

    @Override
    public IValue interpret(EnvStoreTuple tuple) {
        return tuple.lookup(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PALVar palVar = (PALVar) o;
        return var.equals(palVar.var);
    }

    @Override
    public int hashCode() {
        return Objects.hash(var);
    }
}
