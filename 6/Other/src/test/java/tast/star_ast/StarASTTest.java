package tast.star_ast;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tast.TASTVar;
import type.TypeInt;

import static org.junit.jupiter.api.Assertions.*;


class StarASTTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void toJSONString() {
        StarAST starAST = new StarAST(new TASTVar("hi"),new TypeInt());
        assertEquals("{\"expr\":\"hi\",\"type\":\"int\"}", starAST.toJSONString());
    }
}