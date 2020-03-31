package staticDistance;

import interpreter.pal.*;
import interpreter.utils.staticDistance.StaticDistanceEnvironment;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ToyDeclArrayTest {
    Decl decl1, decl2, decl3, decl4, decl5, decl6, decl7, decl1R, decl2R, decl3R, decl5R, decl6R, decl7R;
    ToyDeclArray test1, test2, result2, test3;


    @Before
    public void setUp() throws Exception {
        decl1 = new Decl(new ToyVar("one"), new ToyInt(1L));
        decl2 = new Decl(new ToyVar("two"), new ToyInt(2L));
        decl3 = new Decl(new ToyVar("three"), new ToyInt(3L));
        decl1R = new Decl(new ToyVar("_"), new ToyInt(1L));
        decl2R = new Decl(new ToyVar("_"), new ToyInt(2L));
        decl3R = new Decl(new ToyVar("_"), new ToyInt(3L));
        decl4 = new Decl(new ToyVar("four"), new ToyFunc(Arrays.asList(), new ToyVar("one")));
        test1 = new ToyDeclArray(Arrays.asList(decl1, decl2, decl3), new ToyVar("two"));
        test2 = new ToyDeclArray(Arrays.asList(decl1, decl4, decl3), new ToyVar("one"));
        result2 = new ToyDeclArray(Arrays.asList(decl1R, new Decl(new ToyVar("_"), new ToyFunc(Arrays.asList(), new ToySD(2, 0))), decl3R), new ToySD(2, 0));
        decl5 = new Decl(new ToyVar("x"), new ToyInt(1L));
        decl6 = new Decl(new ToyVar("x"), new ToyInt(5L));
        decl7 = new Decl(new ToyVar("func"), new ToyFunc(Arrays.asList(), new ToyVar("x")));
        decl5R = new Decl(new ToyVar("x"), new ToyInt(1L));
        decl6R = new Decl(new ToyVar("x"), new ToyInt(5L));
        decl7R = new Decl(new ToyVar("func"), new ToyFunc(Arrays.asList(), new ToyVar("x")));
        test3 = new ToyDeclArray(Arrays.asList(decl6, decl7), new ToyDeclArray(Arrays.asList(decl5), new ToyCall(new ToyVar("func"), Arrays.asList())));

    }

    @Test
    public void computeStaticDistance() {
        assertEquals(new ToyDeclArray(Arrays.asList(decl1R, decl2R, decl3R), new ToySD(2, 1)), test1.computeStaticDistance(4, new StaticDistanceEnvironment()));
        assertEquals(result2, test2.computeStaticDistance(4, new StaticDistanceEnvironment()));
//        assertEquals("", test3.computeStaticDistance(4, new StaticDistanceEnvironment()));
    }
}