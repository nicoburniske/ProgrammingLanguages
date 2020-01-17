import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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

    public Decl sd(Map<String, Stack<AccumulatorType>> acc, int depth, int width) {
        return new Decl(v, expr.sd(acc, depth));
    }
    public void updateAcc(Map<String, Stack<AccumulatorType>> acc, int depth, int width) {
        Stack<AccumulatorType> stack;
        if(acc.get(v.s) == null){
            stack = new Stack<AccumulatorType>();
        } else {
            stack = acc.get(v.s);
        }
        stack.push(new AccumulatorType(depth, width));
        acc.put(v.s, stack);
    }

    public void removeFromAcc(Map<String, Stack<AccumulatorType>> acc) {
        acc.get(v.s).pop();
    }
}
