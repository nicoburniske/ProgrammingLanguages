package type;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeIntTest {

    @Test
    void toJSONString() {
        assertEquals("\"int\"", new TypeInt().toJSONString());
    }
}