package interpreter.pal;

import interpreter.utils.EnvStoreTuple;
import interpreter.utils.ValueEnvStoreTuple;
import interpreter.value.ValueInt;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class PALTest {
    EnvStoreTuple stdLib;

    @Before
    public void init() {
        stdLib = EnvStoreTuple.stdLib();
    }

    @Test
    public void testInterpret() {
        Decl d1 = new Decl(new PALVar("x"), new PALFunc(Arrays.asList(new PALVar("param1")), new PALCall(new PALVar("+"), Arrays.asList(new PALVar("param1"), new PALVar("y")))));
        Decl d2 = new Decl(new PALVar("y"), new PALInt(5L));
        Decl d3 = new Decl(new PALVar("z"), new PALInt(100L));

        PALDeclArray darr1 = new PALDeclArray(Arrays.asList(d1, d2, d3), new PALCall(new PALVar("x"), Arrays.asList(new PALVar("z"))));

        assertEquals(new ValueInt(105L), darr1.interpret(stdLib).getLeft());

    }

    @Test
    public void testInterpret2() {
        Decl d1 = new Decl(new PALVar("x"), new PALFunc(Arrays.asList(new PALVar("z")), new PALCall(new PALVar("+"), Arrays.asList(new PALVar("y"), new PALInt(5L)))));
        Decl d2 = new Decl(new PALVar("y"), new PALInt(5L));
        Decl d3 = new Decl(new PALVar("z"), new PALInt(100L));

        PALDeclArray darr1 = new PALDeclArray(Arrays.asList(d1, d2, d3), new PALCall(new PALVar("x"), Arrays.asList(new PALVar("z"))));

        assertEquals(new ValueInt(10L), darr1.interpret(stdLib).getLeft());
    }

    @Test
    public void testHard1() {
        Decl dec = new Decl(new PALVar("a"), new PALFunc(Arrays.asList(new PALVar("a"), new PALVar("b")), new PALCall(new PALVar("+"), Arrays.asList(new PALVar("a"), new PALVar("b")))));
        PALDeclArray acc = new PALDeclArray(Arrays.asList(dec), new PALCall(new PALVar("a"), Arrays.asList(new PALInt(42L), new PALInt(5L))));
        assertEquals(new ValueInt(47L), acc.interpret(stdLib).getLeft());
    }

    /**
     * let x = 5;
     * (call ! (call @ x))
     * should return 5
     */
    @Test
    public void testHard2() {
        Decl d1 = new Decl(new PALVar("x"), new PALInt(5L));
        PALCall call1 = new PALCall(new PALVar("!"), Arrays.asList(new PALCall(new PALVar("@"), Arrays.asList(new PALVar("x")))));
        PALDeclArray arr = new PALDeclArray(Arrays.asList(d1), call1);
        assertEquals(new ValueInt(5L), arr.interpret(stdLib).getLeft());
    }

    @Test
    public void testHard3() {
        Decl d1 = new Decl(new PALVar("x"), new PALInt(5L));
        PALCall call1 = new PALCall(new PALVar("="), Arrays.asList(new PALCall(new PALVar("@"), Arrays.asList(new PALVar("x"))), new PALInt(10L)));
        PALDeclArray arr = new PALDeclArray(Arrays.asList(d1), call1);
        ValueEnvStoreTuple result = arr.interpret(stdLib);
        assertEquals(new ValueInt(5L), result.getLeft());
        assertEquals(new ValueInt(10L), result.getRight().getRight().get(7));
    }
}