package ast.expression;

import ast.Var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EnvStoreTuple;
import value.IValueInt;

import static org.junit.jupiter.api.Assertions.*;

class VarExprTest {

    VarExpr expr1;
    IValueInt result1;
    EnvStoreTuple one;

    @BeforeEach
    void setUp() {
        expr1 = new VarExpr("one");
        result1 = new IValueInt(9);
        one = new EnvStoreTuple().insert(new Var("one"), new IValueInt(9));
    }

    @Test
    void expressionInterpret() {
        assertEquals(result1, expr1.expressionInterpret(one));
    }
}