package ast.expression;

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
}
