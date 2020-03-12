package typechecker.tast;

import org.json.simple.JSONArray;
import typechecker.tast.star_ast.StarAST;
import typechecker.type.Type;
import typechecker.type.TypeFunction;
import typechecker.type.TypedVar;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Override
    public String toJava(Type type) {
        if(parameters.size() > 0) {
            return toJavaHelper(this.parameters, type);
        } else {
            TypeFunction functionType = (TypeFunction) type;
            return String.format("(new %s() {\n@Override\n" +
                    "            public %s get() {\nreturn %s;\n}\n})",
                    functionType.toJava(),
                    functionType.removeOneArg().toJava(),
                    this.body.toJava());

        }
    }

    private String toJavaHelper(List<TypedVar> params, Type type) {
        if(params.size() > 1) {
            TypeFunction functionType = (TypeFunction) type;
            Type returnType = functionType.removeOneArg();
            return String.format("(new %s() {\n@Override\n" +
                            "            public %s apply(%s) {\nreturn %s;\n}\n})",
                    functionType.toJava(),
                    returnType.toJava(),
                    params.get(0).toJava(),
                    toJavaHelper(
                            params.subList(1, params.size()),
                            returnType));
        } else {
            TypeFunction functionType = (TypeFunction) type;
            Type returnType = functionType.removeOneArg();
            return String.format("(new %s() {\n@Override\n" +
                            "            public %s apply(%s) {\nreturn %s;\n}\n})",
                    functionType.toJava(),
                    returnType.toJava(),
                    params.get(0).toJava(),
                    this.body.toJava());
        }
    }
}
