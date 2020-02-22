package interpreter.value;

import java.util.List;

public interface IBin {
    IValue call(List<IValue> vals);
}
