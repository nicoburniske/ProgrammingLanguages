public class VInt implements VExpr{
    public VInt(Integer anInt) {
        this.anInt = anInt;
    }

    Integer anInt;

    @Override
    public VExpr sd() {
        return null;
    }

    @Override
    public String toJson() {
        return anInt.toString();
    }
}
