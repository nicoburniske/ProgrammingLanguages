package interpreter;

import java.util.Map;
import java.util.Stack;

public class VarPair implements VExpr {
    Integer depth;
    Integer pos;

    public VarPair(Integer depth, Integer pos) {
        this.depth = depth;
        this.pos = pos;
    }

    @Override
    public VExpr sd(Map<String, Stack<AccumulatorType>> acc, int depth) {
        //Should never be called
        return null;
    }

    @Override
    public String toJson() {
        return "[" + depth + "," + pos + "]";
    }

    @Override
    public int evaluate(StackList<StackList<Integer>> env) {
        return env.get(depth).get(pos);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VarPair varPair = (VarPair) o;

        if (!depth.equals(varPair.depth)) return false;
        return pos.equals(varPair.pos);
    }

    @Override
    public int hashCode() {
        int result = depth.hashCode();
        result = 31 * result + pos.hashCode();
        return result;
    }
}
