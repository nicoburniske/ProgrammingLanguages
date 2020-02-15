package tpal;

import java.util.List;

public class TPALFunc implements TPAL {
    List<TPALVar> parameters;
    TPAL function;

    public TPALFunc(List<TPALVar> parameters, TPAL function) {
        this.parameters = parameters;
        this.function = function;
    }
}
