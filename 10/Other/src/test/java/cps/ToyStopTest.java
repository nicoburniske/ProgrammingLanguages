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

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void splitExpression() {
    }

    @Test
    public void testInterpret() {
//        ValueClosure val = (ValueClosure) s1.CPS().interpret(EnvStoreTuple.stdLib()).getLeft();
//        // System.out.println(val.getFunction().toJSONString());
//        ValueInt val2 = (ValueInt) s1.run();
//        // assertEquals(new ValueInt(1L), val);
//
//        ToyInt anInt = new ToyInt(5L);
//        IValue result = anInt.CPS().interpret(EnvStoreTuple.stdLib()).getLeft();

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
        System.out.println(testStopHard2.toJSONString());

        IValue resHard = testStopHard2.run();
        System.out.println(resHard.toJSONString());
        assertEquals(new ValueInt(1L), resHard);
    }
}