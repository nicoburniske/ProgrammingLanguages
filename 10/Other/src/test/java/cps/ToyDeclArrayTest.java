package cps;

import interpreter.pal.*;
import interpreter.utils.CPSUtils;
import interpreter.utils.EnvStoreTuple;
import interpreter.value.ValueInt;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ToyDeclArrayTest {
    ToyDeclArray ex1, ex2, ex3, ex4;
    Toy test4;
    Toy result1, result2, result3, result4;
    @Before
    public void setUp() throws Exception {
        ex1 = new ToyDeclArray(Arrays.asList(), new ToyInt(42L));
        ex2 = new ToyDeclArray(Arrays.asList(new Decl(new ToyVar("x"), new ToyInt(4L))), new ToyVar("x"));
        ex3 = new ToyDeclArray(Arrays.asList(new Decl(new ToyVar("x"), new ToyInt(4L)), new Decl( new ToyVar("y"), new ToyFunc(Arrays.asList(), new ToyVar("x")))), new ToyCall(new ToyVar("y"), new ArrayList<>()));
        ex4 = new ToyDeclArray(Arrays.asList(), new ToyFunc(Arrays.asList(), new ToyInt(3L)));
        test4 = new ToyCall(ex4, Arrays.asList());

        result1 = new ToyDeclArray(new ArrayList<>(), new ToyFunc(CPSUtils.KList, new ToyCall(CPSUtils.K, new ToyInt(42L))));
        result2 = new ToyDeclArray(Arrays.asList(new Decl(new ToyVar("x"), new ToyInt(4L))), new ToyFunc(CPSUtils.KList, new ToyCall(CPSUtils.K, Arrays.asList(new ToyVar("x")))));
        result3 = new ToyDeclArray(Arrays.asList(new Decl(new ToyVar("x"), new ToyInt(4L)), new Decl(new ToyVar("y"), new ToyFunc(CPSUtils.KList, new ToyCall(CPSUtils.K, new ToyVar("x"))))),
                new ToyFunc(CPSUtils.KList, new ToyCall(new ToyFunc(CPSUtils.KList, new ToyCall(CPSUtils.K, new ToyVar("y"))),
                        new ToyFunc(Arrays.asList(new ToyVar("of-f")), new ToyCall(new ToyCall(new ToyVar("of-f"), CPSUtils.K), CPSUtils.K)))));
        result4 = new ToyFunc(CPSUtils.KList, new ToyCall(new ToyDeclArray(Arrays.asList(), new ToyFunc(CPSUtils.KList, new ToyCall(CPSUtils.K, new ToyFunc(CPSUtils.KList, new ToyCall(CPSUtils.K, new ToyInt(3L)))))),
                new ToyFunc(Arrays.asList(new ToyVar("yeet")), new ToyCall( new ToyVar("yeet"), CPSUtils.K))));
    }

    @Test
    public void CPS() {
        assertTrue(Toy.alphaEquals(ex1.CPS(), result1));
        assertTrue(Toy.alphaEquals(ex2.CPS(), result2));
        assertTrue(Toy.alphaEquals(test4.CPS(), result4));
    }

    @Test
    public void testInterpretable() {
        assertEquals(new ValueInt(42L), CPSUtils.toTestFormat(ex1.CPS()).interpret(EnvStoreTuple.stdLib()).getLeft());
        assertEquals(new ValueInt(4L), CPSUtils.toTestFormat(ex2.CPS()).interpret(EnvStoreTuple.stdLib()).getLeft());
        System.out.println(ex3.toJSONString());
        System.out.println(CPSUtils.toTestFormat(ex3.CPS()).toJSONString());
//        ValueLambdaClosure val = (ValueLambdaClosure) CPSUtils.toTestFormat(ex3.CPS()).interpret(EnvStoreTuple.stdLib()).getLeft();
//        EnvStoreTuple envstore = CPSUtils.toTestFormat(ex3.CPS()).interpret(EnvStoreTuple.stdLib()).getRight();
//        ValueClosure val2 = (ValueClosure) val.apply(envstore).getLeft();
//        System.out.println(val2.getFunction().toJSONString());

        assertEquals(new ValueInt(4L), CPSUtils.toTestFormat(ex3.CPS()).interpret(EnvStoreTuple.stdLib()).getLeft());
        assertEquals(new ValueInt(3L), CPSUtils.toTestFormat(test4.CPS()).interpret(EnvStoreTuple.stdLib()).getLeft());
    }

}