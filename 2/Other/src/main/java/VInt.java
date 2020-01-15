import java.util.Map;

public class VInt implements VExpr{
    public VInt(Integer anInt) {
        this.anInt = anInt;
    }

    Integer anInt;

    @Override
    public VExpr sd(Map<String,Integer> accDepth, Map<String,Integer> accDecl) {
        return null;
    }

    @Override
    public String toJson() {
        return anInt.toString();
    }
}
