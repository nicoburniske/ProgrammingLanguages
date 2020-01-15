import java.util.List;
import java.util.Map;

public interface VExpr {

    /**
     * replaces every variable reference with a pair of integers.
     * The first integer counts how far away the variable’s declaration is in terms of surrounding Decl JSON arrays,
     * the second one how far from the left the declaration is within this array. Both counts start at 0.
     * A variable without declaration is replaced by itself.
     * @return
     */
    public VExpr sd(Map<String,Integer> accDepth, Map<String,Integer> accDecl);

    public String toJson();
}
