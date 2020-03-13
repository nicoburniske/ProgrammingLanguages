package typechecker.type;

import org.json.simple.JSONArray;

import javax.naming.PartialResultException;
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

    @Override
    public String toJava() {
        if (this.args.size() > 0)
            return toJavaHelper(this.args, this.rhs);
        else {
            return "Supplier<" + this.rhs.toJava() + ">";
        }
    }

    /**
     * This function helps to curry the types so that the Java types make sense
     * @param arguments the arguments that need to be curried into the Java type
     * @param outputType the return type of the TypeFunction
     * @return A Java formatted Function<> type that corresponds to this Type
     */
    public static String toJavaHelper(List<Type> arguments, Type outputType) {
        if(arguments.size() == 0) {
            return outputType.toJava();
        } else {
            return "Function<" + arguments.get(0).toJava() + "," + toJavaHelper(arguments.subList(1,arguments.size()), outputType) + ">";
        }
    }


    /**
     * This function helps to create curried types as the creation of anonymous classes occurs
      * @return the Function minus 1 Argument (if there are more than one) else the rhs of the Function
     */
    public Type removeOneArg() {
        if(this.args.size() == 1) {
            return this.rhs;
        } else if (this.args.size() > 0){
            return new TypeFunction(this.args.subList(1, this.args.size()), this.rhs);
        } else {
            return this.rhs;
        }
    }
}
