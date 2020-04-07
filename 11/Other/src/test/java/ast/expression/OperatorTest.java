package ast.expression;

import ast.Var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EnvStoreTuple;
import utils.exceptions.IntExpectedException;
import value.IValue;
import value.IValueInt;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class OperatorTest {

    Operator op1, op2;
    Int one, two;
    VarExpr three;
    IValueInt result1, result2;
    EnvStoreTuple envStoreTuple;
    @BeforeEach
    void setUp() {
        one = new Int(1);
        two = new Int(new BigInteger("22222222222222222222222"));
        three  = new VarExpr("three");
        op1 = new Operator(one, two, "+");
        result1 = new IValueInt(new BigInteger("22222222222222222222223"));
        op2 = new Operator(two, three, "*");
        result2 = new IValueInt(new BigInteger("44444444444444444444444"));
        envStoreTuple = new EnvStoreTuple().insert(new Var("three"), new IValueInt(2));

    }

    @Test
    void expressionInterpret() {
        assertEquals(result1, op1.expressionInterpret(new EnvStoreTuple()));
        assertThrows(IntExpectedException.class, () -> op2.expressionInterpret(new EnvStoreTuple()));
        assertEquals(result2, op2.expressionInterpret(envStoreTuple));
    }
}