package interpreter.pal;

import interpreter.utils.EnvStoreTuple;
import interpreter.utils.ValueEnvStoreTuple;
import interpreter.value.ValueInt;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ToyIntTest {
    EnvStoreTuple stdLib;
    @Before
    public void init() {
        stdLib = EnvStoreTuple.stdLib();
    }

    @Test
    public void interpret() {
         assertEquals(new ValueEnvStoreTuple(new ValueInt(42L), stdLib), new ToyInt(42L).interpret(stdLib));
    }
}