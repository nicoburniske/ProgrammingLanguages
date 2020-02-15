package tast;

import org.json.simple.JSONArray;
import tast.star_ast.StarAST;

import java.util.List;

public class TASTFuncCall implements TAST {
    StarAST func;
    List<StarAST> arguments;

    public TASTFuncCall(StarAST func, List<StarAST> arguments) {
        this.func = func;
        this.arguments = arguments;
    }

    @Override
    public String toJSONString() {
        JSONArray arr = new JSONArray();
        arr.add("call");
        arr.add(func);
        arr.addAll(arguments);
        return arr.toJSONString();
    }
}
