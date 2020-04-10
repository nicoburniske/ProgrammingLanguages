package ast.decl;

import ast.Var;
import ast.expression.Int;
import ast.expression.VarExpr;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.env.StaticCheckEnv;
import utils.exceptions.TypeCheckException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class IDeclTest {
    Decl d1, dx;
    ArrDecl arrD1;
    StaticCheckEnv mt, containsX;

    @BeforeEach
    void setUp() {
        d1 = new Decl("x", new Int(5));
        dx = new Decl("x", new VarExpr("x"));
        arrD1 = new ArrDecl("y", Arrays.asList(new Int(5), new VarExpr("x")));
        mt = new StaticCheckEnv();
        containsX = mt.put(new Var("x"));
    }

    @Test
    void typecheck() {
        assertEquals(d1, d1.staticCheck(mt));
        assertEquals(arrD1, arrD1.staticCheck(containsX));
        assertThrows(TypeCheckException.class, () -> dx.staticCheck(mt));
        assertEquals(dx, dx.staticCheck(containsX));
    }
}