package tpal.decl;

import env.IEnv;
import env.Tuple;
import tast.TAST;
import tpal.TPAL;
import tpal.TPALVar;
import type.Type;
import type.TypedVar;

import java.util.Objects;

public class TPALDecl {
    TypedVar var;
    TPAL rhs;

    public TPALDecl(TypedVar var, TPAL rhs) {
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


    public Tuple<TAST, IEnv> typeCheck(IEnv<TPALVar, Type> env) {
    }
}
