package tpal;

import type.Type;

public class TVar  extends TPALVar{
    //String var;
    Type type;

    public TVar(String var, Type type) {
        super(var);
        this.type = type;
    }
}
