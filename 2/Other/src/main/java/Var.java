import java.util.Map;

public class Var implements VExpr {
    String s;

    public Var(String s) {
        this.s = s;
    }

    @Override
    public VExpr sd(Map<String, AccumulatorType> acc, int depth) {
        if(acc.get(s) == null) {
            return new Var(s);
        } else {
            return new VarPair(acc.get(s).depth, acc.get(s).width);
        }
    }

    @Override
    public String toJson() {
        return "\"" + s + "\"";
    }
}
