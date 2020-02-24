package interpreter.pal;

import interpreter.utils.EnvStoreTuple;
import interpreter.utils.ValueEnvStoreTuple;
import interpreter.value.ValueInt;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class PALDeclArrayTest {

    EnvStoreTuple stdLib;
    @Before
    public void init() {
        stdLib = EnvStoreTuple.stdLib();
    }
    @Test
    public void testInterpret2() {
        Decl d1 = new Decl(new PALVar("x"), new PALCall(new PALVar("+"), Arrays.asList(new PALInt(5L), new PALVar("y"))));
        Decl d2 = new Decl(new PALVar("y"), new PALInt(5L));
        Decl d3 = new Decl(new PALVar("z"), new PALInt(100L));

        PALDeclArray darr1 = new PALDeclArray(Arrays.asList(d1, d2, d3), new PALCall(new PALVar("x"), Arrays.asList(new PALVar("z"))));

        assertEquals(new ValueEnvStoreTuple(new ValueInt(10L), stdLib), darr1.interpret(stdLib));

    }
}