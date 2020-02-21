package interpreter.pal;

import interpreter.value.IValue;
import interpreter.utils.EnvStoreTuple;

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
    public IValue interpret(EnvStoreTuple tuple) {
        return null;
    }
}
