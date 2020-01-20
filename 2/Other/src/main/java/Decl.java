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
    public <T> void updateAcc(Map<String, Stack<T>> acc, T value) {
        Stack<T> stack;
        if(acc.get(v.s) == null){
            stack = new Stack<T>();
        } else {
            stack = acc.get(v.s);
        }
        stack.push(value);
        acc.put(v.s, stack);
    }

    public <T> void removeFromAcc(Map<String, Stack<T>> acc) {
        acc.get(v.s).pop();
    }

    public int evaluate(Map<String, Stack<Integer>> acc) {
        return expr.evaluate(acc);
    }
}
