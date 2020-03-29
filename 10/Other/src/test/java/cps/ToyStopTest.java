package cps;

import interpreter.pal.Toy;
import interpreter.pal.ToyInt;
import interpreter.pal.ToyStop;
import interpreter.utils.CPSUtils;
import interpreter.utils.EnvStoreTuple;
import interpreter.value.IValue;
import interpreter.value.ValueInt;
import org.junit.Before;
import org.junit.Test;

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
        System.out.println(s1.toJSONString());
        System.out.println(s1.CPS().toJSONString());
        System.out.println(CPSUtils.toTestFormat(s1.CPS()).toJSONString());
        IValue val = s1.CPS().interpret(EnvStoreTuple.stdLib()).getLeft();
        assertEquals(new ValueInt(1L), val);

    }
}