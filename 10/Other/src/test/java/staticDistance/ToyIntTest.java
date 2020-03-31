package staticDistance;

import interpreter.pal.ToyInt;
import interpreter.pal.ToyVar;
import interpreter.utils.staticDistance.StaticDistanceEnvironment;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ToyIntTest {

    ToyInt basicUnit;

    @Before
    public void before() {
        basicUnit = new ToyInt(23L);
    }

    @Test
    public void computeStaticDistance() {
        assertEquals(basicUnit, basicUnit.computeStaticDistance(34, new StaticDistanceEnvironment()));
    }
}