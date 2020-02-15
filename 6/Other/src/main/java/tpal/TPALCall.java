package tpal;

import java.util.List;
import java.util.Objects;

public class TPALCall implements TPAL  {
    TPAL function;
    List<TPAL> arguments;

    public TPALCall(TPAL function, List<TPAL> arguments) {
        this.function = function;
        this.arguments = arguments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TPALCall tpalCall = (TPALCall) o;
        return function.equals(tpalCall.function) &&
                arguments.equals(tpalCall.arguments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(function, arguments);
    }

    @Override
    public String toString() {
        return "TPALCall{" +
                "function=" + function +
                ", arguments=" + arguments +
                '}';
    }
}
