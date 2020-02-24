package interpreter.pal;

import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.EnvStoreTuple;

import java.util.Objects;

public class PALVar implements PAL {
    private String var;

    public PALVar(String var) {
        this.var = var;
    }

    @Override
    public ValueEnvStoreTuple interpret(EnvStoreTuple tuple) {
        return new ValueEnvStoreTuple(tuple.lookup(this), tuple);
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
