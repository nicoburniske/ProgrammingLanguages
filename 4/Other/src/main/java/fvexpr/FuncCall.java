package fvexpr;

import java.util.List;

public class FuncCall implements FVExpr{
    FVExpr func;
    List<FVExpr> params;
}
