package interpreter.utils;

import interpreter.pal.ToyStop;
import interpreter.value.IValue;

/**
 * Represents an exception that is thrown in the interpretation of a {@link ToyStop}.
 * Holds an {@link IValue} that represents the interpretation of the {@link ToyStop}
 */
public class StopInterpretException extends RuntimeException {
    private IValue result;

    public StopInterpretException(IValue result) {
        this.result = result;
    }
    public IValue getResult() {
        return result;
    }
}
