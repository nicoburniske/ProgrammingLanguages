package tast;

import org.json.simple.JSONArray;
import tast.star_ast.StarAST;
import type.TVar;

import java.util.List;

public class TASTFunc implements TAST {
    List<TVar> parameters;
    StarAST body;

    public TASTFunc(List<TVar> parameters, StarAST body) {
        this.parameters = parameters;
        this.body = body;
    }

    @Override
    public String toJSONString() {
        JSONArray arr = new JSONArray();
        arr.add("fun*");
        arr.add(parameters);
        arr.add(body);
        return arr.toJSONString();
    }
}
