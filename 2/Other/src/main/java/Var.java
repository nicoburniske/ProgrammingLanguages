import java.util.Map;

public class Var implements VExpr {
    String s;

    public Var(String s) {
        this.s = s;
    }

    @Override
    public VExpr sd(Map<String,Integer> accDepth, Map<String,Integer> accDecl) {
        return null;
    }

    @Override
    public String toJson() {
        return "\"" + s + "\"";
    }
}
