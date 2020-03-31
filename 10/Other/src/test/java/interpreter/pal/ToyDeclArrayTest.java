package interpreter.pal;

import interpreter.utils.EnvStoreTuple;
import interpreter.value.ValueInt;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ToyDeclArrayTest {

    EnvStoreTuple stdLib;

    @Before
    public void init() {
        stdLib = EnvStoreTuple.stdLib();
    }

    @Test
    public void testInterpret1() {
        Decl d1 = new Decl(new ToyVar("x"), new ToyFunc(Arrays.asList(new ToyVar("param1")), new ToyCall(new ToyVar("+"), Arrays.asList(new ToyVar("param1"), new ToyVar("y")))));
        Decl d2 = new Decl(new ToyVar("y"), new ToyInt(5L));
        Decl d3 = new Decl(new ToyVar("z"), new ToyInt(100L));

        ToyDeclArray darr1 = new ToyDeclArray(Arrays.asList(d1, d2, d3), new ToyCall(new ToyVar("x"), Arrays.asList(new ToyVar("z"))));

        assertEquals(new ValueInt(105L), darr1.interpret(stdLib).getLeft());
    }

    @Test
    public void testInterpret2() {
        Decl d1 = new Decl(new ToyVar("x"), new ToyInt(42L));
        Decl d2 = new Decl(new ToyVar("x"), new ToyInt(20L));
        ToyFunc f1 = new ToyFunc(Arrays.asList(), new ToyVar("x"));
        Decl d3 = new Decl(new ToyVar("fx"), f1);
        ToyCall call = new ToyCall(new ToyVar("fx"), Arrays.asList());
        ToyDeclArray declArr = new ToyDeclArray(Arrays.asList(d1, d3), new ToyDeclArray(Arrays.asList(d2), call));

        assertEquals(new ValueInt(42L), declArr.interpret(stdLib).getLeft());
    }
}