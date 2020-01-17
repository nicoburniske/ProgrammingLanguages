import java.util.Map;
import java.util.Stack;

public class Var implements VExpr {
    String s;

    public Var(String s) {
        this.s = s;
    }

    @Override
    public VExpr sd(Map<String, Stack<AccumulatorType>> acc, int depth) {
        if(acc.get(s) == null || acc.get(s).empty()) {
            return new Var(s);
        } else {
            return new VarPair(depth - acc.get(s).peek().depth, acc.get(s).peek().width);
        }
    }

    @Override
    public String toJson() {
        return "\"" + s + "\"";
    }
}
