package cps;

import interpreter.pal.*;
import interpreter.utils.CPSUtils;
import interpreter.utils.EnvStoreTuple;
import interpreter.value.IValue;
import interpreter.value.ValueClosure;
import interpreter.value.ValueInt;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ToyStopTest {

    ToyStop s1 = new ToyStop(new ToyInt(1L));

    @Test
    public void testInterpret() {
        /**
         * [call
         *   [fun f
         *     [node +
         *           [call f 1]
         *           42]]
         *   [fun x {stop x}]]
         */
        ToyCall testStopHard = new ToyCall(
                new ToyFunc(Arrays.asList(new ToyVar("f")), new ToyCall(new ToyVar("+"),
                                                    Arrays.asList(new ToyCall(new ToyVar("f"), new ToyInt(1L)),new ToyInt(42L)))),
                new ToyFunc(Arrays.asList(new ToyVar("x")), new ToyStop(new ToyVar("x"))));
        // replace call to func with stop
        ToyCall testStopHard2 =  new ToyCall(new ToyVar("+"),
                        Arrays.asList(new ToyStop(new ToyInt(1L)),new ToyInt(42L)));
        IValue resHard = testStopHard.run();
        assertEquals(new ValueInt(1L), resHard);
    }
}