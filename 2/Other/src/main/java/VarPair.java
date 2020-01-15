public class VarPair implements VExpr {
    Integer depth;
    Integer pos;

    public VarPair(Integer depth, Integer pos) {
        this.depth = depth;
        this.pos = pos;
    }

    @Override
    public VExpr sd() {
        //Should never be called
        return null;
    }

    @Override
    public String toJson() {
        return "(" + depth + "," + pos + ")";
    }
}
