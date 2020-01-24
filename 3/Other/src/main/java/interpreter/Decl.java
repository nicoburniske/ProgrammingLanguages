package interpreter;

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

    public void evaluate(HashMap<String, Stack<VExpr>> vars) {
        if(vars.get(v.s) == null) {
            vars.put(v.s, new Stack<VExpr>());
        }
        vars.get(v.s).push(new VInt(expr.evaluate()));
    }

    public Decl substitute(String variable, VExpr value) {
        return new Decl(v, expr.substitute(variable, value));
    }


        @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Decl decl = (Decl) o;

        if (!v.equals(decl.v)) return false;
        return expr.equals(decl.expr);
    }

    @Override
    public int hashCode() {
        int result = v.hashCode();
        result = 31 * result + expr.hashCode();
        return result;
    }
}
