package staticDistance;

import interpreter.pal.*;
import interpreter.utils.staticDistance.StaticDistanceEnvironment;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ToyCallTest {

    ToyCall basicTest;
    ToyFunc basicFunc;

    @Before
    public void setUp() throws Exception {
        basicFunc = new ToyFunc(Arrays.asList(new ToyVar("one"), new ToyVar("two")), new ToyVar("two"));
        basicTest = new ToyCall(basicFunc, Arrays.asList(new ToyInt(1L), new ToyInt(2L)));
    }

    @Test
    public void computeStaticDistance() {
        ToyCall basicResult = new ToyCall(new ToyFunc(Arrays.asList(new ToyVar("one"), new ToyVar("two")), new ToySD(1,1)), Arrays.asList(new ToyInt(1L), new ToyInt(2L)));
        assertEquals(basicResult,basicTest.computeStaticDistance(3, new StaticDistanceEnvironment()));
    }


}