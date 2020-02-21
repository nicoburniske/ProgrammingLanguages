package interpreter.pal;

import interpreter.value.IValue;
import interpreter.utils.EnvStoreTuple;

/**
 * Represents an untyped XPAL expression that should have been typechecked previously
 */
public interface PAL {
    //TODO: Document interface
    IValue interpret (EnvStoreTuple tuple);
}
