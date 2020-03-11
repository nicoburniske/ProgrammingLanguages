package typechecker.tast;

import org.json.simple.JSONArray;
import typechecker.tast.star_ast.StarAST;
import typechecker.tpal.TPAL;
import typechecker.type.Type;

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

    @Override
    public String toJava(Type type) {
        if (arguments.size() > 0) {
            String argString = this.arguments.stream()
                    .map(StarAST::toJava)
                    .reduce("", (acc, val) -> acc + ".apply(" + val + ")");
            return String.format("(%s)%s", this.func.toJava(), argString);
        } else {
            return String.format("(%s)%s", this.func.toJava(), ".get()");
        }
    }
}
