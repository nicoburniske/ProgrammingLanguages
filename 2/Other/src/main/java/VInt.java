import java.util.Map;

public class VInt implements VExpr{
    public VInt(Integer anInt) {
        this.anInt = anInt;
    }

    Integer anInt;

    @Override
    public VExpr sd(Map<String, AccumulatorType> acc, int depth) {
        return new VInt(anInt);
    }

    @Override
    public String toJson() {
        return anInt.toString();
    }
}
