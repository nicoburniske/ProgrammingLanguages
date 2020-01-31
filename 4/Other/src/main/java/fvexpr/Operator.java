package fvexpr;

public abstract class Operator implements FVExpr {
    FVExpr left;
    FVExpr right;

    public Operator(FVExpr left, FVExpr right) {
        this.left = left;
        this.right = right;
    }
}
