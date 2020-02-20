package typecheck.type;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TypeIntTest {

    @Test
    public void toJSONString() {
        assertEquals("\"int\"", new TypeInt().toJSONString());
    }
}