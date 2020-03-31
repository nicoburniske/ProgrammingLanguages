package interpreter.pal;

import interpreter.utils.EnvStoreTuple;
import interpreter.utils.ValueEnvStoreTuple;
import interpreter.value.ValueInt;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ToyTest {
    EnvStoreTuple stdLib;

    @Before
    public void init() {
        stdLib = EnvStoreTuple.stdLib();
    }

    @Test
    public void testInterpret() {
        Decl d1 = new Decl(new ToyVar("x"), new ToyFunc(Arrays.asList(new ToyVar("param1")), new ToyCall(new ToyVar("+"), Arrays.asList(new ToyVar("param1"), new ToyVar("y")))));
        Decl d2 = new Decl(new ToyVar("y"), new ToyInt(5L));
        Decl d3 = new Decl(new ToyVar("z"), new ToyInt(100L));

        ToyDeclArray darr1 = new ToyDeclArray(Arrays.asList(d1, d2, d3), new ToyCall(new ToyVar("x"), Arrays.asList(new ToyVar("z"))));

        assertEquals(new ValueInt(105L), darr1.interpret(stdLib).getLeft());

    }

    @Test
    public void testInterpret2() {
        Decl d1 = new Decl(new ToyVar("x"), new ToyFunc(Arrays.asList(new ToyVar("z")), new ToyCall(new ToyVar("+"), Arrays.asList(new ToyVar("y"), new ToyInt(5L)))));
        Decl d2 = new Decl(new ToyVar("y"), new ToyInt(5L));
        Decl d3 = new Decl(new ToyVar("z"), new ToyInt(100L));

        ToyDeclArray darr1 = new ToyDeclArray(Arrays.asList(d1, d2, d3), new ToyCall(new ToyVar("x"), Arrays.asList(new ToyVar("z"))));

        assertEquals(new ValueInt(10L), darr1.interpret(stdLib).getLeft());
    }

    @Test
    public void testHard1() {
        Decl dec = new Decl(new ToyVar("a"), new ToyFunc(Arrays.asList(new ToyVar("a"), new ToyVar("b")), new ToyCall(new ToyVar("+"), Arrays.asList(new ToyVar("a"), new ToyVar("b")))));
        ToyDeclArray acc = new ToyDeclArray(Arrays.asList(dec), new ToyCall(new ToyVar("a"), Arrays.asList(new ToyInt(42L), new ToyInt(5L))));
        assertEquals(new ValueInt(47L), acc.interpret(stdLib).getLeft());
    }

    /**
     * let x = 5;
     * (call ! (call @ x))
     * should return 5
     */
    @Test
    public void testHard2() {
        Decl d1 = new Decl(new ToyVar("x"), new ToyInt(5L));
        ToyCall call1 = new ToyCall(new ToyVar("!"), Arrays.asList(new ToyCall(new ToyVar("@"), Arrays.asList(new ToyVar("x")))));
        ToyDeclArray arr = new ToyDeclArray(Arrays.asList(d1), call1);
        assertEquals(new ValueInt(5L), arr.interpret(stdLib).getLeft());
        // x = 5, ! (@ x)
    }

    @Test
    public void testHard3() {
        Decl d1 = new Decl(new ToyVar("x"), new ToyInt(5L));
        ToyCall call1 = new ToyCall(new ToyVar("="), Arrays.asList(new ToyCall(new ToyVar("@"), Arrays.asList(new ToyVar("x"))), new ToyInt(10L)));
        ToyDeclArray arr = new ToyDeclArray(Arrays.asList(d1), call1);
        ValueEnvStoreTuple result = arr.interpret(stdLib);
        assertEquals(new ValueInt(5L), result.getLeft());
        assertEquals(new ValueInt(10L), result.getRight().getRight().get(7));
    }
}