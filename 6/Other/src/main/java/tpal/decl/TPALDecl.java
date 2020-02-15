package tpal.decl;

import tpal.TPAL;
import tpal.TPALVar;
import type.TVar;

import java.util.Objects;

public class TPALDecl {
    TVar var;
    TPAL rhs;

    public TPALDecl(TVar var, TPAL rhs) {
        this.var = var;
        this.rhs = rhs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TPALDecl tpalDecl = (TPALDecl) o;
        return var.equals(tpalDecl.var) &&
                rhs.equals(tpalDecl.rhs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(var, rhs);
    }

    @Override
    public String toString() {
        return "TPALDecl{" +
                "var=" + var +
                ", rhs=" + rhs +
                '}';
    }
}
