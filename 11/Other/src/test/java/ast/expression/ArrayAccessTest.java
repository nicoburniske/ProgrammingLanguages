package ast.expression;

import ast.Var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EnvStoreTuple;
import value.IValue;
import value.IValueInt;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayAccessTest {

    ArrayAccess arr1;
    VarExpr arrVar1;
    Int int1;
    EnvStoreTuple containsA;
    IValueInt result1;

    @BeforeEach
    void setUp() {
        int1 = new Int(1);
        arrVar1 = new VarExpr("a");
        arr1 = new ArrayAccess(arrVar1, int1);
        List<IValue> values = Arrays.asList(new IValueInt(1), new IValueInt(2));
        containsA = new EnvStoreTuple().insert(new Var("a"), values);
        result1 = new IValueInt(2);
    }

    @Test
    void expressionInterpret() {
        assertEquals(result1, arr1.expressionInterpret(containsA));
    }
}