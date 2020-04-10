package ast.expression;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EnvStoreTuple;
import value.IValue;
import value.IValueInt;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class IntTest{

    Int one, two;
    IValueInt oneR, twoR;

    @BeforeEach
    void setUp() {
        one = new Int(1);
        oneR = new IValueInt(1);
        two = new Int(new BigInteger("2000000000000000"));
        twoR = new IValueInt(new BigInteger("2000000000000000"));

    }

    @Test
    void expressionInterpret() {
        assertEquals(oneR, one.expressionInterpret(new EnvStoreTuple()));
        assertEquals(twoR, two.expressionInterpret(new EnvStoreTuple()));
    }
}