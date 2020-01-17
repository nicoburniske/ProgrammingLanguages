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
}
