import java.util.Map;
import java.util.Stack;

public interface VExpr {

    /**
     * replaces every variable reference with a pair of integers.
     * The first integer counts how far away the variable’s declaration is in terms of surrounding Decl JSON arrays,
     * the second one how far from the left the declaration is within this array. Both counts start at 0.
     * A variable without declaration is replaced by itself.
     *
     * @param acc
     * @param depth
     * @return
     */
    public VExpr sd(Map<String, Stack<AccumulatorType>> acc, int depth);

    /**
     * Used to convert a VExpr into a String in JSON format as specified by the requirement "unparse" for sd.
     * Any previously defined variable will be represented as a JSON array consisting of a pair of integers.
     * The first describes how many decl arrays are in between the originating decl array and its current position.
     * The second represents its position within the decl array that it was initialized.
     *
     * A variable without declaration is left by itself.
     *
     * @return A String in JSON format representing a VExpr.
     */
    public String toJson();

    /**
     *
     * @param acc
     * @return
     * @throws IllegalStateException
     */
    public int evaluate(Map<String, Stack<Integer>> acc) throws IllegalStateException;
}
