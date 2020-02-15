package tpal;

import type.TVar;

import java.util.List;

public class TPALFunc implements TPAL {
    List<TVar> parameters;
    TPAL function;

    public TPALFunc(List<TVar> parameters, TPAL function) {
        this.parameters = parameters;
        this.function = function;
    }
}
