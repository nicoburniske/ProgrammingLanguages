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
}
