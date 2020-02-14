package type;

import tast.TASTVar;

public class TVar {
    TASTVar var;
    Type type;

    public TVar(TASTVar var, Type type) {
        this.var = var;
        this.type = type;
    }
}
