package tpal.decl;

import tpal.TPAL;
import tpal.TPALVar;

public class TPALDecl {
    TPALVar var;
    TPAL rhs;

    public TPALDecl(TPALVar var, TPAL rhs) {
        this.var = var;
        this.rhs = rhs;
    }
}
