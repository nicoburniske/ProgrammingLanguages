package typecheck.tpal.decl;

import typecheck.env.IEnv;
import typecheck.env.Tuple;
import typecheck.env.TupleGeneric;
import typecheck.tast.star_decl.StarDecl;
import typecheck.tpal.TPAL;
import typecheck.tpal.TPALFunc;
import typecheck.tpal.TPALVar;
import typecheck.type.Type;
import typecheck.type.TypedVar;

import java.util.Objects;

import static typecheck.utils.Constants.ERROR_DECL_TYPE_MATCHING;

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
