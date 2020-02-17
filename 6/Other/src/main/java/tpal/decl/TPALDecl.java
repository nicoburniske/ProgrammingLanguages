package tpal.decl;

import env.IEnv;
import env.Tuple;
import env.TupleGeneric;
import tast.star_decl.StarDecl;
import tpal.TPAL;
import tpal.TPALFunc;
import tpal.TPALVar;
import type.Type;
import type.TypedVar;

import java.util.Objects;

import static utils.Constants.ERROR_DECL_TYPE_MATCHING;

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

    public TypedVar getVar() {
        return var;
    }

    public TPAL getRhs() {
        return rhs;
    }


    public TupleGeneric<StarDecl, IEnv<TPALVar, Type>> typeCheck(IEnv<TPALVar, Type> env) {
        Tuple varTuple = this.var.typeCheck(env);
        if(rhs instanceof TPALFunc) {
            env = varTuple.getRight();
        }
        Tuple rhsTuple = rhs.typeCheck(env);
        if (varTuple.getLeft().getType().equals(rhsTuple.getLeft().getType())) {
            env = varTuple.getRight();
            return new TupleGeneric<>(new StarDecl(this.var, rhsTuple.getLeft()), env);
        } else {
            throw new IllegalStateException(ERROR_DECL_TYPE_MATCHING);
        }
    }

}
