package interpreter.pal;

import interpreter.utils.EnvStoreTuple;
import interpreter.value.ValueInt;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PALVarTest {

    EnvStoreTuple stdLib;
    @Before
    public void init() {
       stdLib = EnvStoreTuple.stdLib();
    }
    @Test
    public void interpret() {
        stdLib = stdLib.insert(new PALVar("x"), new ValueInt(42L));
        assertEquals(new ValueInt(42), new PALVar("x").interpret(stdLib));
    }
}