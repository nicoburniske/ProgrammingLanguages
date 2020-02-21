package typechecker.tpal.decl;

import common.LookupTable;
import interpreter.pal.Decl;
import interpreter.pal.PALVar;
import typechecker.env.Tuple;
import common.TupleGeneric;
import typechecker.tast.star_decl.StarDecl;
import typechecker.tpal.TPAL;
import typechecker.tpal.TPALFunc;
import typechecker.tpal.TPALVar;
import typechecker.type.Type;
import typechecker.type.TypedVar;

import java.util.Objects;

import static typechecker.utils.Constants.ERROR_DECL_TYPE_MATCHING;

// TODO: enforce restriction that the rhs must be integer literal or fun*
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


    public TupleGeneric<StarDecl, LookupTable<TPALVar, Type>> typeCheck(LookupTable<TPALVar, Type> env) {
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

    public Decl fillet() {
        return new Decl(new PALVar(this.var.getVar()),this.rhs.fillet());
    }
}
