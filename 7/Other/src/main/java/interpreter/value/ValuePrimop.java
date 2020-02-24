package interpreter.value;

import interpreter.utils.EnvStoreTuple;
import interpreter.utils.ValueEnvStoreTuple;

import java.util.List;
import java.util.Objects;

public class ValuePrimop implements IValue{
    private IBin op;
    private int arity;

    public ValuePrimop(int arity, IBin op) {
        this.op = op;
        this.arity = arity;
    }
    public ValueEnvStoreTuple apply (List<IValue> args, EnvStoreTuple tuple) {
        return op.call(args, tuple);
    }

    @Override
    public String toJSONString() {
        return "\"primop\"";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValuePrimop that = (ValuePrimop) o;
        return arity == that.arity &&
                op.equals(that.op);
    }

    @Override
    public int hashCode() {
        return Objects.hash(op, arity);
    }
}
