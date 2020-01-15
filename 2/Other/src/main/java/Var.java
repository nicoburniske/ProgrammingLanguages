public class Var implements VExpr {
    String s;

    public Var(String s) {
        this.s = s;
    }

    @Override
    public VExpr sd() {
        return null;
    }

    @Override
    public String toJson() {
        return "\"" + s + "\"";
    }
}
