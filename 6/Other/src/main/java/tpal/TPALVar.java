package tpal;

import java.util.Objects;

public class TPALVar implements TPAL {
    protected String var;

    public TPALVar(String var) {
        this.var = var;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TPALVar tpalVar = (TPALVar) o;
        return var.equals(tpalVar.var);
    }

    @Override
    public int hashCode() {
        return Objects.hash(var);
    }

    @Override
    public String toString() {
        return "TPALVar{" +
                "var='" + var + '\'' +
                '}';
    }
}
