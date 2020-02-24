package interpreter.pal;

import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.EnvStoreTuple;
import interpreter.value.ValueInt;

import java.math.BigInteger;

public class PALInt implements PAL {
    private BigInteger num;

    public PALInt(BigInteger num) {
        this.num = num;
    }

    public PALInt(Long num) {
        this.num = new BigInteger(String.valueOf(num));
    }

    @Override
    public ValueEnvStoreTuple interpret(EnvStoreTuple tuple) {
        return new ValueEnvStoreTuple(new ValueInt(this.num), tuple);
    }
}
