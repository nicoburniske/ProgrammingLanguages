package staticDistance;

import interpreter.pal.ToySD;
import interpreter.pal.ToyVar;
import interpreter.utils.staticDistance.StaticDistanceEnvironment;
import interpreter.utils.staticDistance.TupleSD;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ToyVarTest {
    ToyVar basicUnit;
    @Before
    public void before(){
        basicUnit = new ToyVar("var");
    }

    @Test
    public void computeStaticDistance() {
        assertEquals(basicUnit,basicUnit.computeStaticDistance(0, new StaticDistanceEnvironment()));
        assertEquals(new ToySD(5, 4),basicUnit.computeStaticDistance(0, new StaticDistanceEnvironment().put(new ToyVar("var"),new TupleSD(5, 4))));
        assertEquals(new ToySD(3, 4),basicUnit.computeStaticDistance(2, new StaticDistanceEnvironment().put(new ToyVar("var"),new TupleSD(5, 4))));

    }
}