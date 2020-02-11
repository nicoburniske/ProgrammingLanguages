package fvexpr;

import answer.Answer;
import org.junit.Before;
import org.junit.Test;
import store.Store;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class ConditionalTest {
    SFVExpr ifTrue, ifFalse;

    @Before
    public void init(){
       this.ifFalse = new Conditional(new Int((long) 0), new Int((long) 10), new Int((long) 25));
       this.ifTrue = new Conditional(new Int((long) 1), new Int((long) 10), new Int((long) 25));
    }

    @Test
    public void testInterpret() {
        assertEquals(new BigInteger("10"), this.ifFalse.interpret(new Store(),new Store()).result);
        assertEquals(new BigInteger("25"), this.ifTrue.interpret(new Store(),new Store()).result);
    }
}
