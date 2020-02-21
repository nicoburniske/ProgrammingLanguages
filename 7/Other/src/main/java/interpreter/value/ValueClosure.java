package interpreter.value;

import interpreter.pal.PALFunc;

public class ValueClosure implements IValue {
    private PALFunc function;

    public ValueClosure(PALFunc function) {
        this.function = function;
    }

    @Override
    public String toJSONString() {
        return null;
    }
}
