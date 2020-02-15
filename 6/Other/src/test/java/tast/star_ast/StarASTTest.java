package tast.star_ast;

import org.junit.Before;
import org.junit.Test;
import tast.TASTVar;
import type.TypeInt;

import static org.junit.Assert.assertEquals;


public class StarASTTest {
    @Before
    public void setUp() {
    }

    @Test
    public void toJSONString() {
        StarAST starAST = new StarAST(new TASTVar("hi"),new TypeInt());
        assertEquals("{\"expr\":\"hi\",\"type\":\"int\"}", starAST.toJSONString());
    }
}