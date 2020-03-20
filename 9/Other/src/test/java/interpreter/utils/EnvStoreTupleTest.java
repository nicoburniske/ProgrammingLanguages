package interpreter.utils;

import interpreter.pal.PALVar;
import interpreter.utils.env.Environment;
import interpreter.utils.store.Store;
import interpreter.value.ValueInt;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnvStoreTupleTest {
    EnvStoreTuple std;
    EnvStoreTuple empty;

    @Before
    public void setup() {
        std = EnvStoreTuple.stdLib();
        empty = new EnvStoreTuple(new Environment(), new Store());
    }

    @Test
    public void lookup() {
//        std.lookup("+")
    }

    @Test
    public void insert() {
        assertEquals(
                new EnvStoreTuple(
                    new Environment(new PALVar("q"), 0),
                    new Store(0, new ValueInt(1L))),
                empty.insert(new PALVar("q"),new ValueInt(1L)));
    }

    @Test
    public void stdLib() {
    }
}