package type;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

class TypeIntTest {

    @Test
    void toJSONString() {
        assertEquals("\"int\"", new TypeInt().toJSONString());
    }
}