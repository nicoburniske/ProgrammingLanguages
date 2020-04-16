package ast.decl;

import ast.expression.Expression;
import ast.Var;
import org.json.simple.JSONArray;
import utils.env.StaticCheckEnv;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ArrDecl implements IDecl<List<Expression>> {
    private Var var;
    private List<Expression> value;
    //Example of how it will look in the store
    //0 location is ref
    //1 location is arrayTag and lenght
    //2 and on are the array
    //[let x = [1,2,3]][y = x]
    //[x][y]
    //[0][5]
    //[0][1][2][3][4][5]
    //[1][3,2][1][2][3][1]

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
    public IDecl staticCheck(StaticCheckEnv environment) {
        List<Expression> arr = this.value.stream().map(v -> v.staticCheck(environment)).collect(Collectors.toList());
        return new ArrDecl(this.var, arr);
    }

    @Override
    public Var getVar() {
        return this.var;
    }

    @Override
    public List<Expression> getRHS() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrDecl arrDecl = (ArrDecl) o;
        return var.equals(arrDecl.var) &&
                value.equals(arrDecl.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(var, value);
    }


}
