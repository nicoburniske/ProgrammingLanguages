package typechecker.tast;

import org.junit.Test;
import typechecker.tpal.TPALVar;
import typechecker.type.TypeInt;

import static org.junit.Assert.*;

public class TASTVarTest {

    @Test
    public void toJSONString() {
        assertEquals("\"x\"", new TASTVar("x").toJSONString());
    }

    @Test
    public void toJava() {
        TASTVar var = new TASTVar("a");
        assertEquals("a", var.toJava(new TypeInt()));
    }
}