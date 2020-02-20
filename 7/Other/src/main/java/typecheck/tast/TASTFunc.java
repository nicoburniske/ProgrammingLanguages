package typecheck.tast;

import org.json.simple.JSONArray;
import typecheck.tast.star_ast.StarAST;
import typecheck.type.TypedVar;

import java.util.List;
import java.util.Objects;

public class TASTFunc implements TAST {
    List<TypedVar> parameters;
    StarAST body;

    public TASTFunc(List<TypedVar> parameters, StarAST body) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TASTFunc tastFunc = (TASTFunc) o;
        return parameters.equals(tastFunc.parameters) &&
                body.equals(tastFunc.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameters, body);
    }
}
