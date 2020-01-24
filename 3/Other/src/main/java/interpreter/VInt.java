package interpreter;

import java.util.Map;
import java.util.Stack;

public class VInt implements VExpr{
    public VInt(Integer anInt) {
        this.anInt = anInt;
    }

    Integer anInt;

    @Override
    public VExpr sd(Map<String, Stack<AccumulatorType>> acc, int depth) {
        return new VInt(anInt);
    }

    @Override
    public String toJson() {
        return anInt.toString();
    }

    @Override
    public int evaluate() {
        return anInt;
    }

    @Override
    public VExpr substitute(String variable, VExpr value) {
        return new VInt(anInt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VInt vInt = (VInt) o;

        return anInt.equals(vInt.anInt);
    }

    @Override
    public int hashCode() {
        return anInt.hashCode();
    }
}
