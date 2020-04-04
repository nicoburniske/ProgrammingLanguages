package ast.var_decl;

import ast.expression.Expression;
import ast.Var;
import org.json.simple.JSONArray;

import java.util.Arrays;
import java.util.List;

public class VarArrDecl implements Decl {
    private Var var;
    private List<Expression> value;

    public VarArrDecl(Var var, List<Expression> value) {
        this.var = var;
        this.value = value;
    }


    public VarArrDecl(String var, List<Expression> value) {
        this.var = new Var(var);
        this.value = value;
    }

    public VarArrDecl(String var, Expression value) {
        this.var = new Var(var);
        this.value = Arrays.asList(value);
    }

    @Override
    public String toString() {
        return this.toJSONString();
    }

    @Override
    public String toJSONString() {
        JSONArray arr = new JSONArray();
        arr.add("vec");
        arr.add(var);
        arr.add("=");
        arr.addAll(value);
        return arr.toJSONString();
    }
}
