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
    public VExpr sd(Map<String, AccumulatorType> acc, int depth) {
        return new VOperator(left.sd(acc, depth), right.sd(acc, depth), op);
    }

    @Override
    public String toJson() {
        return "[" + left.toJson() + ",\"" + op + "\"," + right.toJson() + "]";
    }
}
