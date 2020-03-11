package typechecker.tpal;

import common.LookupTable;
import typechecker.env.Tuple;
import typechecker.tast.TASTVar;
import typechecker.tast.star_ast.StarAST;
import typechecker.type.Type;

import java.util.Objects;

import static typechecker.utils.Constants.ERROR_UNDECLARED_VARIABLE_TEMPLATE;

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
    public Tuple typeCheck(LookupTable<TPALVar, Type> env) {
        Type t = env.get(this);
        if(t == null) {
            throw new IllegalStateException(String.format(ERROR_UNDECLARED_VARIABLE_TEMPLATE, this.var));
        } else {
            return new Tuple(new StarAST(new TASTVar(this.var), t), env);
        }
    }

    @Override
    public String toJava() {
        return this.var.toString();
    }

    public String getVar() {
        return var;
    }
}
