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

        assertEquals(new ValueEnvStoreTuple( new ValueInt(105L), stdLib), darr1.interpret(stdLib));

    }
    @Test
    public void testInterpret2() {
        Decl d1 = new Decl(new PALVar("x"), new PALCall(new PALVar("+"), Arrays.asList(new PALInt(5L), new PALVar("y"))));
        Decl d2 = new Decl(new PALVar("y"), new PALInt(5L));
        Decl d3 = new Decl(new PALVar("z"), new PALInt(100L));

        PALDeclArray darr1 = new PALDeclArray(Arrays.asList(d1, d2, d3), new PALCall(new PALVar("x"), Arrays.asList(new PALVar("z"))));

        assertEquals(new ValueEnvStoreTuple(new ValueInt(10L), stdLib), darr1.interpret(stdLib));
    }

    @Test
    public void testHard1() {
        Decl dec = new Decl(new PALVar("a"), new PALFunc(Arrays.asList(new PALVar("a"), new PALVar("b")), new PALCall(new PALVar("+"), Arrays.asList(new PALVar("a"), new PALVar("b")))));
        PALDeclArray acc = new PALDeclArray(Arrays.asList(dec), new PALCall(new PALVar("a"), Arrays.asList(new PALInt(42L), new PALInt(5L))));
        assertEquals(new ValueEnvStoreTuple(new ValueInt(47L), stdLib), acc.interpret(stdLib));
    }
}