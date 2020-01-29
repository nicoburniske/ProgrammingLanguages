package fvexpr;

import fdecl.FDecl;

import java.util.List;

public class DeclArray  implements FVExpr{
    List<FDecl> decls;
    FVExpr scope;
}
