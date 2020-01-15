import java.util.Map;

public class VOperator implements VExpr{
    VExpr left;
    VExpr right;
    String op;

    public VOperator(VExpr left, VExpr right, String op) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    @Override
    public VExpr sd(Map<String,Integer> accDepth, Map<String,Integer> accDecl) {
        return null;
    }

    @Override
    public String toJson() {
        return "[" + left.toJson() + ",\"" + op + "\"," + right.toJson() + "]";
    }
}
