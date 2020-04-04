package ast.expression;

import ast.Var;
import utils.env.Environment;

import java.util.Objects;

public class VarExpr implements Expression{
    Var var;

    public VarExpr(Var var) {
        this.var = var;
    }

    @Override
    public Expression typecheck(Environment env) {
        return new VarExpr(this.var.typecheck(env));
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
