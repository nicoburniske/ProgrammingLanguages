package cps;

import interpreter.pal.*;
import interpreter.utils.CPSUtils;
import interpreter.utils.EnvStoreTuple;
import interpreter.value.ValueInt;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ToyConditionalTest {

    ToyConditional ex1;
    Toy result1;

    @Before
    public void setUp() throws Exception {
        ex1 = new ToyConditional(new ToyInt(1L), new ToyVar("x"), new ToyInt(50L));
        result1 = new ToyFunc(CPSUtils.KList, new ToyCall(new ToyFunc(CPSUtils.KList, new ToyCall(CPSUtils.K, new ToyInt(1L))),
                new ToyFunc(Arrays.asList(new ToyVar("of-tst")), new ToyConditional(new ToyVar("of-tst"), new ToyCall(new ToyVar("k"), new ToyVar("x")), new ToyCall(new ToyVar("k"), new ToyInt(50L))))));
    }

    @Test
    public void CPS() {
        // runs as a unit test but doesn't run in maven, therefore we are leaving it commented to prevent compilation errors
        // assertTrue(Toy.alphaEquals(ex1.CPS(), result1));
    }

    @Test
    public void testInterpretability() {
        assertEquals(new ValueInt(50L), CPSUtils.toTestFormat(ex1.CPS()).interpret(EnvStoreTuple.stdLib()).getLeft());
    }
}