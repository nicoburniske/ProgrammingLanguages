package type;

import org.json.simple.JSONArray;

import java.util.List;

public class TypeFunction implements Type{
    List<Type> args;
    Type rhs;

    public TypeFunction(List<Type> args, Type rhs) {
        this.args = args;
        this.rhs = rhs;
    }

    @Override
    public String toJSONString() {
        JSONArray arr = new JSONArray();
        arr.addAll(args);
        arr.add("->");
        arr.add(rhs);
        return arr.toJSONString();
    }
}
