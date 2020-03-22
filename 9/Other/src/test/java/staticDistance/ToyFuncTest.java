package staticDistance;

import interpreter.pal.Toy;
import interpreter.pal.ToyFunc;
import interpreter.pal.ToySD;
import interpreter.pal.ToyVar;
import interpreter.utils.staticDistance.StaticDistanceEnvironment;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ToyFuncTest {
    ToyFunc basicUnit;
    ToyFunc basicUnit2;
    @Before
    public void before(){
        basicUnit = new ToyFunc(Arrays.asList(new ToyVar("one")), new ToyVar("one"));
        basicUnit2 = new ToyFunc(Arrays.asList(new ToyVar("one"), new ToyVar("two")), new ToyVar("two"));
    }

    @Test
    public void computeStaticDistance() {
        assertEquals(new ToyFunc(Arrays.asList(new ToyVar("_")), new ToySD(1,0)), basicUnit.computeStaticDistance(3, new StaticDistanceEnvironment()));
        assertEquals(new ToyFunc(Arrays.asList(new ToyVar("_"), new ToyVar("_")), new ToySD(1,1)), basicUnit2.computeStaticDistance(3, new StaticDistanceEnvironment()));

    }
}