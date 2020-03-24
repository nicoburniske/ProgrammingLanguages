package interpreter.utils;

import interpreter.pal.ToyVar;
import org.junit.Test;

import static org.junit.Assert.*;

public class CPSUtilsTest {

    @Test
    public void initializeNames() {
        assertEquals("k", CPSUtils.K.toString());
        ToyVar k = new ToyVar("k");
        CPSUtils.initializeNames(k);
        assertNotEquals("k", CPSUtils.K.toString());
        System.out.println(k.CPS().toJSONString());
        assertEquals(k.CPS().toJSONString().indexOf("\"k\""), k.CPS().toJSONString().lastIndexOf("\"k\""));
    }
}