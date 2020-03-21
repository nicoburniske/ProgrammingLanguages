package interpreter.pal;

import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.EnvStoreTuple;
import interpreter.value.ValueInt;

import java.math.BigInteger;

/**
 * Represents an Integer
 */
public class ToyInt implements Toy {
    private BigInteger num;

    public ToyInt(BigInteger num) {
        this.num = num;
    }

    public ToyInt(Long num) {
        this.num = new BigInteger(String.valueOf(num));
    }

    @Override
    public ValueEnvStoreTuple interpret(EnvStoreTuple tuple) {
        return new ValueEnvStoreTuple(new ValueInt(this.num), tuple);
    }
}
