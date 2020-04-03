package cps;

import interpreter.pal.ToyCall;
import interpreter.pal.ToyGrab;
import interpreter.pal.ToyInt;
import interpreter.pal.ToyVar;
import interpreter.utils.EnvStoreTuple;
import interpreter.value.ValueInt;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ToyGrabTest {
    ToyGrab example1;

    @Before
    public void setUp() throws Exception {
        example1 = new ToyGrab(new ToyVar("done"), new ToyCall(new ToyVar("+"), Arrays.asList(new ToyInt(1L), new ToyCall(new ToyVar("done"), new ToyInt(42L)))));
    }

    /**
     * ["grab", "done"
     * ["call" , "+", "1", ["call", "done", "42"]]]
     */
    @Test
    public void splitExpression() {
        assertEquals(new ValueInt(42L), example1.run());
    }

    @Test
    public void testException() {
        try {
            example1.interpret(EnvStoreTuple.stdLib());
            fail();
        } catch (IllegalStateException e) {
            // should not be able to be interpreted since we are processing it in CPS.
        }
    }
}