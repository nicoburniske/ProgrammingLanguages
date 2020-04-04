package ast.lhs;

import ast.Var;
import utils.env.StaticCheckEnv;

import java.util.Objects;

public class VarLoc implements LHS{
    private Var var;

    public VarLoc(Var var) {
        this.var = var;
    }

    public VarLoc(String s) {
        this.var = new Var(s);
    }

    @Override
    public String toString() {
        return this.toJSONString();
    }

    @Override
    public String toJSONString() {
        return this.var.toJSONString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VarLoc varLoc = (VarLoc) o;
        return var.equals(varLoc.var);
    }

    @Override
    public int hashCode() {
        return Objects.hash(var);
    }

    @Override
    public LHS staticCheck(StaticCheckEnv environment) {
        return new VarLoc(var.typecheck(environment));
    }
}
