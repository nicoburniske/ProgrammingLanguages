package cps;

import interpreter.pal.ToyCall;
import interpreter.pal.ToyFunc;
import interpreter.pal.ToyInt;
import interpreter.pal.ToyVar;
import interpreter.utils.CPSUtils;
import interpreter.utils.EnvStoreTuple;
import interpreter.utils.ValueEnvStoreTuple;
import interpreter.value.ValueClosure;
import interpreter.value.ValueInt;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.EventListener;

import static org.junit.Assert.*;

public class ToyCallTest {
    ToyCall ex1, ex2;
    ToyFunc inputInput;

    @Before
    public void setUp() throws Exception {
        ex1 = new ToyCall(new ToyFunc(Arrays.asList(new ToyVar("input")), new ToyVar("input")), new ToyInt(4L));
        ex2 = new ToyCall(new ToyVar("+"), Arrays.asList(new ToyInt(5L), new ToyInt(100L)));
        inputInput = new ToyFunc(Arrays.asList(new ToyVar("input")), new ToyVar("input"));
    }

    @Test
    public void CPS() {
        // System.out.println(CPSUtils.toTestFormat(ex1.CPS()).toJSONString());
        // System.out.println(CPSUtils.toTestFormat(ex2.CPS()).toJSONString());
        // ValueClosure val = (ValueClosure) CPSUtils.toTestFormat(ex2.CPS()).interpret(EnvStoreTuple.stdLib()).getLeft();
//        System.out.println(ex2.toJSONString());
//        System.out.println(CPSUtils.toTestFormat(ex2.CPS()).toJSONString());
//        System.out.println(inputInput.CPS().toJSONString());
        // System.out.println(val.getFunction().toJSONString());

        // System.out.println(CPSUtils.toTestFormat(val.getFunction()).interpret(EnvStoreTuple.stdLib()).getLeft());
        System.out.println(CPSUtils.toTestFormat(ex1.CPS()).toJSONString());
        assertEquals(new ValueInt(4L), CPSUtils.toTestFormat(ex1.CPS()).interpret(EnvStoreTuple.stdLib()).getLeft());
        System.out.println(CPSUtils.toTestFormat(ex2.CPS()).toJSONString());
        assertEquals(new ValueInt(105L), CPSUtils.toTestFormat(ex2.CPS()).interpret(EnvStoreTuple.stdLib()).getLeft());
    }
}