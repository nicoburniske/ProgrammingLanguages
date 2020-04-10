package ast.expression;

import ast.Var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.env.StaticCheckEnv;
import utils.exceptions.TypeCheckException;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTest {
    Int int1, int2, int3;
    Operator onePlus2, twoPlusThree, onePlusX;
    VarExpr xVar, yVar;
    ArrayAccess arr1;
    StaticCheckEnv mt, containsX;

    @BeforeEach
    void setUp() {
        int1 = new Int(1);
        int2 = new Int(2);
        int3 = new Int(3);
        xVar = new VarExpr("x");
        yVar = new VarExpr("y");

        onePlus2 = new Operator(int1, int2, "+");
        twoPlusThree = new Operator(int2, int3, "+");
        onePlusX= new Operator(int1, xVar, "+");

        arr1 = new ArrayAccess(xVar, int1);

        mt = new StaticCheckEnv();
        containsX = mt.put(new Var("x"));
    }

    @Test
    void typecheck() {
        assertEquals(int1, int1.staticCheck(mt));
        assertEquals(onePlus2, onePlus2.staticCheck(mt));
        assertEquals(onePlusX, onePlusX.staticCheck(containsX));
        assertEquals(arr1, arr1.staticCheck(containsX));

        assertThrows(TypeCheckException.class, () -> this.xVar.staticCheck(mt));
        assertThrows(TypeCheckException.class, () -> this.onePlusX.staticCheck(mt));
        assertThrows(TypeCheckException.class, () -> this.onePlusX.staticCheck(mt));
        assertThrows(TypeCheckException.class, () -> this.arr1.staticCheck(mt));
    }
}