package interpreter.utils;

import interpreter.value.IValue;

public class StopInterpretException extends RuntimeException {
    private IValue result;

    public StopInterpretException(IValue result) {
        this.result = result;
    }
    public IValue getResult() {
        return result;
    }
}
