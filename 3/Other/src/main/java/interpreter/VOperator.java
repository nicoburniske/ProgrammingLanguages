package interpreter;

import java.util.Map;
import java.util.Stack;

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
    public VExpr sd(Map<String, Stack<AccumulatorType>> acc, int depth) {
        return new VOperator(left.sd(acc, depth), right.sd(acc, depth), op);
    }

    @Override
    public String toJson() {
        return "[" + left.toJson() + ",\"" + op + "\"," + right.toJson() + "]";
    }

    @Override
    public int evaluate() {
        if ("+".equals(op)) {
            return left.evaluate() + right.evaluate();
        } else if("*".equals(op)) {
            return left.evaluate() * right.evaluate();
        } else {
            throw new IllegalStateException("Invalid Operator");
        }
    }

    @Override
    public VExpr substitute(String variable, VExpr value) {
        return new VOperator(left.substitute(variable, value), right.substitute(variable, value), op);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VOperator vOperator = (VOperator) o;

        if (!left.equals(vOperator.left)) return false;
        if (!right.equals(vOperator.right)) return false;
        return op.equals(vOperator.op);
    }

    @Override
    public int hashCode() {
        int result = left.hashCode();
        result = 31 * result + right.hashCode();
        result = 31 * result + op.hashCode();
        return result;
    }
}
