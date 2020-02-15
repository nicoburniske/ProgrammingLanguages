package tpal;

import java.util.List;

public class TPALCall implements TPAL  {
    TPAL function;
    List<TPAL> arguments;

    public TPALCall(TPAL function, List<TPAL> arguments) {
        this.function = function;
        this.arguments = arguments;
    }
}
