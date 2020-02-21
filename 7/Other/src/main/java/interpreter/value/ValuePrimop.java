package interpreter.value;

public class ValuePrimop implements IValue{
    private IBin op;
    private int arity;

    public ValuePrimop(int arity, IBin op) {
        this.op = op;
        this.arity = arity;
    }

    @Override
    public String toJSONString() {
        return null;
    }
}
