package interpreter.pal;

import interpreter.utils.EnvStoreTuple;
import interpreter.utils.ValueEnvStoreTuple;
import interpreter.value.ValueClosure;
import interpreter.value.ValueInt;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ToyFuncTest {
    EnvStoreTuple stdLib;

    @Before
    public void init() {
        stdLib = EnvStoreTuple.stdLib();
    }

    @Test
    public void interpret() {
        Decl d1 = new Decl(new ToyVar("x"), new ToyFunc(Arrays.asList(new ToyVar("param1")), new ToyCall(new ToyVar("+"), Arrays.asList(new ToyVar("param1"), new ToyVar("y")))));
        Decl d2 = new Decl(new ToyVar("y"), new ToyInt(5L));
        Decl d3 = new Decl(new ToyVar("z"), new ToyInt(100L));

        ToyDeclArray darr1 = new ToyDeclArray(Arrays.asList(d1, d2, d3), new ToyCall(new ToyVar("x"), Arrays.asList(new ToyVar("z"))));

        assertEquals(new ValueInt(105L), darr1.interpret(stdLib).getLeft());

        ToyFunc func1 = new ToyFunc(Arrays.asList(), new ToyInt(5L));
        assertEquals(new ValueEnvStoreTuple(new ValueClosure(func1, stdLib.getLeft()), stdLib), func1.interpret(stdLib));
    }

    @Test
    public void apply() {

    }
}