package interpreter.pal;

import interpreter.utils.EnvStoreTuple;
import interpreter.value.ValueInt;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PALIntTest {
    EnvStoreTuple stdLib;
    @Before
    public void init() {
        stdLib = EnvStoreTuple.stdLib();
    }

    @Test
    public void interpret() {
         assertEquals(new ValueInt(42L), new PALInt(42L).interpret(stdLib));
    }
}