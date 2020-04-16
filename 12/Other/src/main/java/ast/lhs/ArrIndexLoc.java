package ast.lhs;

import ast.expression.Expression;
import org.json.simple.JSONArray;
import utils.EnvStoreTuple;
import utils.env.StaticCheckEnv;
import utils.exceptions.ArrayIndexException;
import value.*;

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

    @Override
    public LHS staticCheck(StaticCheckEnv environment) {
        return new ArrIndexLoc(this.array.staticCheck(environment), this.index.staticCheck(environment));
    }

    @Override
    public Location lhsInterpreter(EnvStoreTuple tuple) {
        IValue arrValue = this.array.expressionInterpret(tuple);
        IValue indexValue = this.index.expressionInterpret(tuple);
        if(arrValue instanceof IValueReference) {
            arrValue = tuple.getRight().get(((IValueReference) arrValue).getLoc());
        }
        if(arrValue instanceof IValueArray && indexValue instanceof IValueInt) {
            IValueArray arr = (IValueArray) arrValue;
            IValueInt idx = (IValueInt) indexValue;
            return arr.getLocation(idx);
        } else {
            throw new ArrayIndexException();
        }
    }
}
