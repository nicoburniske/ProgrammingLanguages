package ast.decl;

import ast.expression.Expression;
import ast.Var;
import org.json.simple.JSONArray;
import utils.env.Environment;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArrDecl implements IDecl {
    private Var var;
    private List<Expression> value;

    public ArrDecl(Var var, List<Expression> value) {
        this.var = var;
        this.value = value;
    }


    public ArrDecl(String var, List<Expression> value) {
        this.var = new Var(var);
        this.value = value;
    }

    public ArrDecl(String var, Expression value) {
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

    @Override
    public IDecl typecheck(Environment environment) {
        List<Expression> arr = this.value.stream().map(v -> v.typecheck(environment)).collect(Collectors.toList());
        return new ArrDecl(this.var, arr);
    }

    @Override
    public Var getVar() {
        return this.var;
    }
}
