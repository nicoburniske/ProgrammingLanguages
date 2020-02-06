package store;

import org.junit.Test;

import static org.junit.Assert.*;

public class StoreTest {

    @Test
    public void testGetAndPut() {
        Store<Integer, Integer> store = new Store();
        store.put(5,2 );
        assertEquals(store.get(5), new Integer(2));
        store.put(5, 3);
        assertEquals(store.get(5), new Integer(3));
        store.put(1, 8);
        assertEquals(store.get(1), new Integer(8));
        assertEquals(store.get(27), null);
    }

    @Test
    public void testPop() {
        Store<Integer, Integer> store = new Store();
        store.put(5,2 );
        store.put(5, 3);
        store.put(1, 8);
        store.pop();
        assertEquals(store.get(1), null);
        store.pop();
        assertEquals(store.get(5), new Integer(2));
    }
}