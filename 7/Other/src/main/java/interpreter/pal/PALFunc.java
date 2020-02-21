package interpreter.pal;

import interpreter.value.IValue;
import interpreter.utils.EnvStoreTuple;
import interpreter.value.ValueClosure;

import java.util.List;

public class PALFunc implements PAL {
    private List<PALVar> params;
    private PAL function;

    public PALFunc(List<PALVar> params, PAL function) {
        this.params = params;
        this.function = function;
    }

    @Override
    public IValue interpret(EnvStoreTuple tuple) {
        return new ValueClosure(this, tuple.getLeft());
    }

    public List<PALVar> getParams() {
        return params;
    }

    public IValue apply(EnvStoreTuple tuple) {
        return this.function.interpret(tuple);
    }
}
