package ast.var_decl;

import ast.expression.Expression;
import ast.Var;
import org.json.simple.JSONArray;

import java.util.Objects;

public class VarDecl implements Decl {
    private Var var;
    private Expression expression;

    public VarDecl(Var var, Expression expression) {
        this.var = var;
        this.expression = expression;
    }

    public VarDecl(String var, Expression expression) {
        this.var = new Var(var);
        this.expression = expression;
    }

    @Override
    public String toJSONString() {
        JSONArray arr = new JSONArray();
        arr.add("let");
        arr.add(var);
        arr.add("=");
        arr.add(expression);
        return arr.toJSONString();
    }

    @Override
    public String toString() {
        return this.toJSONString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VarDecl varDecl = (VarDecl) o;
        return var.equals(varDecl.var) &&
                expression.equals(varDecl.expression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(var, expression);
    }
}
