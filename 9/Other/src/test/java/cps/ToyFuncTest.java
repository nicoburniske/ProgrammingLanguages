package cps;

import interpreter.pal.Toy;
import interpreter.pal.ToyFunc;
import interpreter.pal.ToyVar;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ToyFuncTest {
    ToyFunc ex1;
    Toy result1;
    @Before
    public void setUp() throws Exception {
        ex1 = new ToyFunc(Arrays.asList(new ToyVar("x"), new ToyVar("y")), new ToyVar("y"));
    }
    @Test
    public void CPS() {
        System.out.println(ex1.CPS().toJSONString());
    }


}