package staticDistance;

import interpreter.pal.ToyConditional;
import interpreter.pal.ToyInt;
import interpreter.pal.ToySD;
import interpreter.pal.ToyVar;
import interpreter.utils.staticDistance.StaticDistanceEnvironment;
import interpreter.utils.staticDistance.TupleSD;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ToyConditionalTest {
    ToyConditional basicTest;
    @Before
    public void setUp() throws Exception {
        basicTest = new ToyConditional(new ToyInt(1L), new ToyVar("left"), new ToyVar("right"));
    }

    @Test
    public void computeStaticDistance() {
        assertEquals(
                new ToyConditional(new ToyInt(1L), new ToySD(2,3), new ToyVar("right")),
                basicTest.computeStaticDistance(3, new StaticDistanceEnvironment().put(new ToyVar("left"), new TupleSD(1, 3))));
    }
}