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
}
