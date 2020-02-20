package typechecker.tast;

import org.junit.Test;

import static org.junit.Assert.*;

public class TASTVarTest {

    @Test
    public void toJSONString() {
        assertEquals("\"x\"", new TASTVar("x").toJSONString());
    }
}