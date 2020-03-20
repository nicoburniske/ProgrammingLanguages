package interpreter.pal;

import interpreter.utils.EnvStoreTuple;
import interpreter.utils.ValueEnvStoreTuple;
import interpreter.value.ValueClosure;
import interpreter.value.ValueInt;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class PALFuncTest {
    EnvStoreTuple stdLib;

    @Before
    public void init() {
        stdLib = EnvStoreTuple.stdLib();
    }

    @Test
    public void interpret() {
        Decl d1 = new Decl(new PALVar("x"), new PALFunc(Arrays.asList(new PALVar("param1")), new PALCall(new PALVar("+"), Arrays.asList(new PALVar("param1"), new PALVar("y")))));
        Decl d2 = new Decl(new PALVar("y"), new PALInt(5L));
        Decl d3 = new Decl(new PALVar("z"), new PALInt(100L));

        PALDeclArray darr1 = new PALDeclArray(Arrays.asList(d1, d2, d3), new PALCall(new PALVar("x"), Arrays.asList(new PALVar("z"))));

        assertEquals(new ValueInt(105L), darr1.interpret(stdLib).getLeft());

        PALFunc func1 = new PALFunc(Arrays.asList(), new PALInt(5L));
        assertEquals(new ValueEnvStoreTuple(new ValueClosure(func1, stdLib.getLeft()), stdLib), func1.interpret(stdLib));
    }

    @Test
    public void apply() {

    }
}