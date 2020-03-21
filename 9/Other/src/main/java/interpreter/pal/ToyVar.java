package interpreter.pal;

import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.EnvStoreTuple;

import java.util.Objects;

/**
 * Represents a Variable in
 */
public class ToyVar implements Toy {
    private String var;

    public ToyVar(String var) {
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
        ToyVar palVar = (ToyVar) o;
        return var.equals(palVar.var);
    }

    @Override
    public int hashCode() {
        return Objects.hash(var);
    }

    // for debugging
    @Override
    public String toString() {
        return this.var;
    }
}
