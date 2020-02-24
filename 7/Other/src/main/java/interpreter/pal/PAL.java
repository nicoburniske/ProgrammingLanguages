package interpreter.pal;

import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.EnvStoreTuple;

/**
 * Represents an untyped XPAL expression that should have been typechecked previously
 */
public interface PAL {
    //TODO: Document interface
    ValueEnvStoreTuple interpret (EnvStoreTuple tuple);
}
