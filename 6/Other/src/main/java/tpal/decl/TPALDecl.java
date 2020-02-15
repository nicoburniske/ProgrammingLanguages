package tpal.decl;

import tpal.TPAL;
import tpal.TPALVar;
import type.TVar;

public class TPALDecl {
    TVar var;
    TPAL rhs;

    public TPALDecl(TVar var, TPAL rhs) {
        this.var = var;
        this.rhs = rhs;
    }
}
