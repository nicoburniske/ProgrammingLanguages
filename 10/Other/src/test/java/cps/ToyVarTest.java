package cps;

import interpreter.pal.Toy;
import interpreter.pal.ToyCall;
import interpreter.pal.ToyFunc;
import interpreter.pal.ToyVar;
import interpreter.utils.CPSUtils;
import interpreter.utils.EnvStoreTuple;
import interpreter.utils.staticDistance.StaticDistanceEnvironment;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static interpreter.utils.RuntimeExceptions.ERROR_UNDECLARED_VARIABLE_TEMPLATE;
import static org.junit.Assert.*;

public class ToyVarTest {
    ToyVar v;
    Toy vResult;

    @Before
    public void setUp() throws Exception {
        v = new ToyVar("v");
        vResult = new ToyFunc(Arrays.asList(new ToyVar("x")), new ToyCall(new ToyVar("x"), new ToyVar("v")));
    }

    @Test
    public void testCPS() {
        assertTrue(Toy.alphaEquals(v.CPS(), vResult));
    }

    @Test
    public void testInterpretability() {
        try {
            CPSUtils.toTestFormat(v.CPS()).interpret(EnvStoreTuple.stdLib());
        } catch (IllegalStateException e) {
            assertEquals("\"variable v undeclared\"", e.getMessage());
        }

        try {
            v.run();
        } catch (IllegalStateException e) {
            assertEquals(String.format(ERROR_UNDECLARED_VARIABLE_TEMPLATE, "v"), e.getMessage());
        }


    }

}