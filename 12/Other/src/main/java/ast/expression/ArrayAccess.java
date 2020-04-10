package ast.expression;

import org.json.simple.JSONArray;
import utils.EnvStoreTuple;
import utils.env.StaticCheckEnv;
import utils.exceptions.ArrayIndexException;
import value.IValue;
import value.IValueArray;
import value.IValueInt;
import value.IValueReference;

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

    @Override
    public IValue expressionInterpret(EnvStoreTuple tuple) {
        IValue arrValue = this.array.expressionInterpret(tuple);
        IValue indexValue = this.index.expressionInterpret(tuple);
        if(arrValue instanceof IValueReference) {
            arrValue = tuple.getRight().get(((IValueReference) arrValue).getLoc());
        }
        if(arrValue instanceof IValueArray && indexValue instanceof IValueInt) {
            IValueArray arr = (IValueArray) arrValue;
            IValueInt idx = (IValueInt) indexValue;
            return arr.get(idx, tuple.getRight());
        } else {
            throw new ArrayIndexException();
        }
    }
}
