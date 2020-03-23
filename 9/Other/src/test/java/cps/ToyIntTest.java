package cps;

import interpreter.pal.*;
import interpreter.utils.EnvStoreTuple;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ToyIntTest {
    ToyInt ex1;
    Toy result1;

    @Before
    public void init(){
        ex1 = new ToyInt(5L);
        result1 = new ToyFunc(Arrays.asList(new ToyVar("k")), new ToyCall(new ToyVar("k"), new ToyInt(5L)));

    }
    @Test
    public void CPS() {
       //System.out.println(ex1.CPS().toJSONString());
       //System.out.println(result1.toJSONString());
       assertTrue(Toy.alphaEquals(ex1.CPS(), result1));
       System.out.println(new ToyCall(ex1.CPS(), new ToyFunc(Arrays.asList(new ToyVar("crap")), new ToyVar("crap"))).interpret(EnvStoreTuple.stdLib()).getLeft().toJSONString());
       // System.out.println(new ToyCall(ex1.CPS(), new ToyInt(10L)).interpret(EnvStoreTuple.stdLib()));
    }
}