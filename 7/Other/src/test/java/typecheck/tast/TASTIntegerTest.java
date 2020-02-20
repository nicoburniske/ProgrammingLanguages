package typecheck.tast;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class TASTIntegerTest {

    @Test
    public void toJSONString() {
        assertEquals("1", new TASTInteger(new BigInteger("1")).toJSONString());
    }
}