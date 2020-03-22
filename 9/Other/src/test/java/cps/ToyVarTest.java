package cps;

import interpreter.pal.Toy;
import interpreter.pal.ToyCall;
import interpreter.pal.ToyFunc;
import interpreter.pal.ToyVar;
import interpreter.utils.staticDistance.StaticDistanceEnvironment;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

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
        System.out.println(v.CPS().toJSONString());
        // System.out.println(vResult.computeStaticDistance(0, new StaticDistanceEnvironment()).toJSONString());
        assertTrue(Toy.alphaEquals(v.CPS(), vResult));
    }

    @Test
    public void computeStaticDistance() {

    }

    @Test
    public void splitExpression() {

    }
}