package ast.lhs;

import ast.expression.Expression;
import org.json.simple.JSONArray;

import java.util.Objects;

public class ArrIndexLoc implements LHS {
    private Expression array;
    private Expression index;

    public ArrIndexLoc(Expression array, Expression index) {
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
        ArrIndexLoc that = (ArrIndexLoc) o;
        return array.equals(that.array) &&
                index.equals(that.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(array, index);
    }
}
