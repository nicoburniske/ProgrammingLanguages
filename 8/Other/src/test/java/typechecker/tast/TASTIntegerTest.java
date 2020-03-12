package typechecker.tast;

import org.junit.Test;
import typechecker.tpal.TPALInt;
import typechecker.type.TypeInt;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class TASTIntegerTest {

    @Test
    public void toJSONString() {
        assertEquals("1", new TASTInteger(new BigInteger("1")).toJSONString());
    }
    @Test
    public void toJava() {
        TASTInteger i = new TASTInteger(123);
        assertEquals("new MyInteger(123)", i.toJava(new TypeInt()));
    }
}