package interpreter.pal;

import interpreter.utils.ValueEnvStoreTuple;
import interpreter.utils.EnvStoreTuple;
import interpreter.utils.staticDistance.StaticDistanceEnvironment;
import interpreter.value.ValueInt;

import java.math.BigInteger;
import java.util.Objects;

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

    @Override
    public Toy computeStaticDistance(int currDepth, StaticDistanceEnvironment env) {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToyInt toyInt = (ToyInt) o;
        return num.equals(toyInt.num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num);
    }
}
