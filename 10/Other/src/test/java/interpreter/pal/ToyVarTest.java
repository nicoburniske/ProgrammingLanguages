package interpreter.pal;

import interpreter.utils.EnvStoreTuple;
import interpreter.utils.ValueEnvStoreTuple;
import interpreter.value.ValueInt;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ToyVarTest {

    EnvStoreTuple stdLib;

    @Before
    public void init() {
        stdLib = EnvStoreTuple.stdLib();
    }

    @Test
    public void interpret() {
        stdLib = stdLib.insert(new ToyVar("x"), new ValueInt(42L));
        assertEquals(new ValueEnvStoreTuple(new ValueInt(42), stdLib), new ToyVar("x").interpret(stdLib));
    }
}