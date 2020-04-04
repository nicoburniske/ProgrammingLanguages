package ast.expression;

import ast.Var;
import utils.EnvStoreTuple;
import utils.env.StaticCheckEnv;
import value.IValue;

import java.util.Objects;

public class VarExpr implements Expression{
    Var var;

    public VarExpr(Var var) {
        this.var = var;
    }

    public VarExpr(String x) {
        this.var = new Var(x);
    }

    @Override
    public Expression staticCheck(StaticCheckEnv env) {
        return new VarExpr(this.var.typecheck(env));
    }

    @Override
    public IValue expressionInterpret(EnvStoreTuple tuple) {
        //We know it is declared because of {@link staticCheck}
        return tuple.lookup(this.var);
    }

    @Override
    public String toJSONString() {
        return this.var.toJSONString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VarExpr varExpr = (VarExpr) o;
        return var.equals(varExpr.var);
    }

    @Override
    public int hashCode() {
        return Objects.hash(var);
    }

    @Override
    public String toString() {
        return this.toJSONString();
    }
}
