package value;

import java.util.List;

public class IValueArray implements IValue{
    List<IValue> value;

    public IValueArray(List<IValue> value) {
        this.value = value;
    }
}
