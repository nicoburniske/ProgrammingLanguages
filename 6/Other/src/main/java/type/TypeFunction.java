package type;

import org.json.simple.JSONArray;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeFunction that = (TypeFunction) o;
        return args.equals(that.args) &&
                rhs.equals(that.rhs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(args, rhs);
    }
}
