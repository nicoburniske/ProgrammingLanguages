package tpal;

import env.IEnv;
import env.Tuple;
import tast.TASTVar;
import tast.star_ast.StarAST;
import type.Type;

import java.util.Objects;

import static constants.Constants.ERROR_UNDECLARED_VARIABLE_TEMPLATE;

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

    @Override
    public Tuple typeCheck(IEnv<TPALVar, Type> env) {
        Type t = env.get(this);
        if(t == null) {
            throw new IllegalStateException(String.format(ERROR_UNDECLARED_VARIABLE_TEMPLATE, this.var));
        } else {
            return new Tuple(new StarAST(new TASTVar(this.var), t), env);
        }
    }

    public String getVar() {
        return var;
    }
}
