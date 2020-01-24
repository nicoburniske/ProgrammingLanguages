package interpreter;

import java.util.Map;
import java.util.Stack;

public class Var implements VExpr {
    String s;

    public Var(String s) {
        this.s = s;
    }

    @Override
    public VExpr sd(Map<String, Stack<AccumulatorType>> acc, int depth) {
        if (acc.get(s) == null || acc.get(s).empty()) {
            return new Var(s);
        } else {
            return new VarPair(depth - acc.get(s).peek().depth, acc.get(s).peek().width);
        }
    }

    @Override
    public String toJson() {
        return "\"" + s + "\"";
    }

    @Override
    public int evaluate() {
        throw new IllegalStateException("\"variable " + s + " undeclared\"");
    }

    @Override
    public VExpr substitute(String variable, VExpr value) {
        if(variable.equals(this.s)) {
            return value;
        } else {
            return new Var(s);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Var var = (Var) o;

        return s.equals(var.s);
    }

    @Override
    public int hashCode() {
        return s.hashCode();
    }
}
