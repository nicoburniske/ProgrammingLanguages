package typecheck.tast;

import org.json.simple.JSONArray;
import typecheck.tast.star_ast.StarAST;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TASTFuncCall that = (TASTFuncCall) o;
        return func.equals(that.func) &&
                arguments.equals(that.arguments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(func, arguments);
    }
}
