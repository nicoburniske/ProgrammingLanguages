package tast.star_ast;

import org.junit.Before;
import org.junit.Test;
import tast.TASTVar;
import type.TypeInt;

import static org.junit.Assert.assertEquals;


class StarASTTest {
    @Before
    void setUp() {
    }

    @Test
    void toJSONString() {
        StarAST starAST = new StarAST(new TASTVar("hi"),new TypeInt());
        assertEquals("{\"expr\":\"hi\",\"type\":\"int\"}", starAST.toJSONString());
    }
}