package typechecker.type;


import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


public class TypeFunctionTest {

    @Test
    public void toJSONString() {
        assertEquals(new TypeFunction(Arrays.asList(new TypeInt(), new TypeInt(), new TypeInt()), new TypeInt()).toJSONString(), "[\"int\",\"int\",\"int\",\"->\",\"int\"]" );
        assertEquals(new TypeFunction(Arrays.asList(new TypeInt(), new TypeInt(), new TypeInt()), new TypeFunction(Arrays.asList(new TypeInt()), new TypeInt())).toJSONString(), "[\"int\",\"int\",\"int\",\"->\",[\"int\",\"->\",\"int\"]]");
    }

    @Test
    public void toJava() {
        TypeFunction f = new TypeFunction(Arrays.asList(new TypeInt(), new TypeInt()), new TypeInt());
        assertEquals("Function<Integer,Function<Integer,Integer>>", f.toJava());
    }
}