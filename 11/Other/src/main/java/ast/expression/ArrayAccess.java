package ast.expression;

import org.json.simple.JSONArray;
import utils.env.StaticCheckEnv;

import java.util.Objects;

public class ArrayAccess implements Expression {
    private Expression array;
    private Expression index;

    public ArrayAccess(Expression array, Expression index) {
        this.array = array;
        this.index = index;
    }

    @Override
    public String toString() {
        return this.toJSONString();
    }

    @Override
    public String toJSONString() {
        JSONArray arr = new JSONArray();
        arr.add(this.array);
        arr.add(this.index);
        return arr.toJSONString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayAccess that = (ArrayAccess) o;
        return array.equals(that.array) &&
                index.equals(that.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(array, index);
    }

    @Override
    public Expression staticCheck(StaticCheckEnv env) {
        return new ArrayAccess(this.array.staticCheck(env), this.index.staticCheck(env));
    }
}
