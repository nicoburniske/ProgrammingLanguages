import java.util.HashMap;
import java.util.Map;

public class Decl {
    Var v;
    VExpr expr;

    public Decl(Var v, VExpr expr) {
        this.v = v;
        this.expr = expr;
    }

    public String toJSON() {
        return "[\"let\"," + v.toJson() + ",\"=\"," + expr.toJson() + "]";
    }

    public Decl sd(Map<String, AccumulatorType> acc, int depth, int width) {
        return new Decl(v, expr.sd(acc, depth));
    }
    public Map<String, AccumulatorType> updateAcc(Map<String, AccumulatorType> acc, int depth, int width) {
        Map<String, AccumulatorType> accCopy = new HashMap<String, AccumulatorType>(acc);
        accCopy.put(v.s, new AccumulatorType(depth, width));
        return accCopy;
    }
}
