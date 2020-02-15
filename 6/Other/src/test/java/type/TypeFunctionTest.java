package type;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TypeFunctionTest {

    @Test
    void toJSONString() {
        assertEquals(new TypeFunction(Arrays.asList(new TypeInt(), new TypeInt(), new TypeInt()), new TypeInt()).toJSONString(), "[\"int\",\"int\",\"int\",\"->\",\"int\"]" );
    }
}