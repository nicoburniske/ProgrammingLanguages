package ast.decl;

import ast.expression.Expression;
import ast.Var;
import org.json.simple.JSONArray;
import utils.env.StaticCheckEnv;

import java.util.Objects;

public class Decl implements IDecl {
    private Var var;
    private Expression expression;

    public Decl(Var var, Expression expression) {
        this.var = var;
        this.expression = expression;
    }

    public Decl(String var, Expression expression) {
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
        Decl varDecl = (Decl) o;
        return var.equals(varDecl.var) &&
                expression.equals(varDecl.expression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(var, expression);
    }

    @Override
    public IDecl staticCheck(StaticCheckEnv environment) {
        return new Decl(this.var, this.expression.staticCheck(environment));
    }

    @Override
    public Var getVar() {
        return this.var;
    }
}
