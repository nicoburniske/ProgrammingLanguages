package ast.lhs;

import ast.Var;
import ast.expression.Int;
import ast.expression.VarExpr;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.env.StaticCheckEnv;
import utils.exceptions.TypeCheckException;

import static org.junit.jupiter.api.Assertions.*;

class LHSTest {
    ArrIndexLoc arr1;
    VarExpr xVar, yVar;
    VarLoc var1, var2;
    StaticCheckEnv mt, containsX;

    @BeforeEach
    void setUp() {
        mt = new StaticCheckEnv();
        containsX = mt.put(new Var("x"));
        xVar = new VarExpr("x");
        yVar = new VarExpr("y");

        var1 = new VarLoc("x");
        var2 = new VarLoc("y");

        arr1 = new ArrIndexLoc(xVar, new Int(0));
    }

    @Test
    void typecheck() {
        assertEquals(var1, this.var1.staticCheck(containsX));
        assertEquals(arr1, this.arr1.staticCheck(containsX));
        assertThrows(TypeCheckException.class, () -> this.var1.staticCheck(mt));
        assertThrows(TypeCheckException.class, () -> this.arr1.staticCheck(mt));
    }
}