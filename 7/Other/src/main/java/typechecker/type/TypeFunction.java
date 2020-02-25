package typechecker.type;

import org.json.simple.JSONArray;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TypeFunction implements Type{
    List<Type> args;
    Type rhs;

    public TypeFunction(List<Type> args, Type rhs) {
        this.args = args;
        this.rhs = rhs;
    }
    public TypeFunction(Type args, Type rhs) {
        this.args = Arrays.asList(args);
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
        boolean equality = args.size() == that.args.size();
        for(int ii = 0; ii < args.size(); ii++) {
            if(!equality) {
                return false;
            }
            equality = equality && args.get(ii).equals(that.args.get(ii));
        }
        return equality &&
                rhs.equals(that.rhs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(args, rhs);
    }

    @Override
    public String toString() {
        return "TypeFunction{" +
                "args=" + args +
                ", rhs=" + rhs +
                '}';
    }

    public List<Type> getArgs() {
        return args;
    }

    public Type getRhs() {
        return rhs;
    }

}
