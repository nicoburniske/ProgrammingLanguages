package fvexpr;

import answer.Answer;
import org.junit.Before;
import org.junit.Test;
import store.Location;
import store.Store;
import store.StoreUtils;

import java.math.BigInteger;

import static junit.framework.Assert.assertEquals;


public class IntTest {
    Store<Var, Location> stdEnv = StoreUtils.initializeStd().get(0); // the "standard library"
    Store<Location, Answer> stdStore = StoreUtils.initializeStd().get(1);
    Int one, two, big;

    @Before
    public void setUp() throws Exception {
        one = new Int(1L);
        two = new Int(2L);
        big = new Int(20000000000000000L);
    }

    @Test
    public void interpreter() {
        assertEquals(one.interpret(stdEnv,stdStore).result, new BigInteger("1"));
        assertEquals(two.interpret(stdEnv,stdStore).result, new BigInteger("2"));
        assertEquals(big.interpret(stdEnv,stdStore).result, new BigInteger("20000000000000000"));
    }
}
