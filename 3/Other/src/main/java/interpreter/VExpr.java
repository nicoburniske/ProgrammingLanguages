package interpreter;

import java.util.Map;
import java.util.Stack;

public interface VExpr {

    /**
     * replaces every variable reference with a pair of integers.
     * The first integer counts how far away the variable’s declaration is in terms of surrounding interpreter.Decl JSON arrays,
     * the second one how far from the left the declaration is within this array. Both counts start at 0.
     * A variable without declaration is replaced by itself.
     *
     * @param acc
     * @param depth
     * @return
     */
    public VExpr sd(Map<String, Stack<AccumulatorType>> acc, int depth);

    /**
     * Used to convert a interpreter.VExpr into a String in JSON format.
     *
     * @return A String in JSON format representing a interpreter.VExpr.
     */
    public String toJson();

    /**
     * consumes a interpreter.VExpr and produces its value, an integer. Substitutes all of the
     * variables and evaluates the expression
     *
     * @return the value of the {@link VExpr}
     * @throws IllegalStateException when a {@link Var} is not defined in the acc and is used.
     * @param env
     */
    public int evaluate(StackList<StackList<Integer>> env) throws IllegalStateException;

}
