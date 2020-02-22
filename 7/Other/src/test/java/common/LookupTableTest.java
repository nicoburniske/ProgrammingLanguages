package common;

import org.junit.Test;

import static org.junit.Assert.*;

public class LookupTableTest {

    @Test
    public void put() {
        LookupTable table = new LookupTableEnd<Integer, String>().put(1, "abc");
        assertEquals(new LookupTablePair(1, "abc", new LookupTableEnd()), table);
    }

    @Test
    public void get() {
        LookupTable table = new LookupTableEnd<Integer, String>().put(1, "abc");
        assertEquals("abc", table.get(1));
    }

    @Test
    public void getSize() {
        LookupTable<Integer, String> table = new LookupTableEnd<Integer, String>().put(1, "abc");
        assertEquals(1L, (table.getSize().longValue()));
    }
}