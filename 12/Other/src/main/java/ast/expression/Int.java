package ast.expression;

import utils.EnvStoreTuple;
import utils.env.StaticCheckEnv;
import utils.exceptions.TypeCheckException;
import value.IValue;
import value.IValueInt;

import java.math.BigInteger;
import java.util.Objects;

public class Int implements Expression {
    private BigInteger integer;

    public Int(BigInteger integer) {
        this.integer = integer;
    }

    public Int(Integer integer) {
        this.integer = new BigInteger(integer.toString());
    }


    @Override
    public String toJSONString() {
        return integer.toString();
    }


    @Override
    public String toString() {
        return this.toJSONString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Int anInt = (Int) o;
        return integer.equals(anInt.integer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(integer);
    }

    @Override
    public Expression staticCheck(StaticCheckEnv env) throws TypeCheckException {
        return new Int(this.integer);
    }

    @Override
    public IValue expressionInterpret(EnvStoreTuple tuple) {
        return new IValueInt(this.integer);
    }
}
