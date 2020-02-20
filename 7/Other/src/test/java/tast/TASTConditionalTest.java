package tast;

import org.junit.Test;
import tast.star_ast.StarAST;
import type.TypeInt;

import static org.junit.Assert.*;

public class TASTConditionalTest {

    @Test
    public void toJSONString() {
        TASTConditional cond = new TASTConditional(new StarAST(new TASTVar("a"),new TypeInt()),new StarAST(new TASTVar("b"),new TypeInt()), new StarAST(new TASTVar("c"),new TypeInt()));
        assertEquals("[\"if-0\",{\"expr\":\"a\",\"type\":\"int\"},{\"expr\":\"b\",\"type\":\"int\"},{\"expr\":\"c\",\"type\":\"int\"}]",cond.toJSONString());
    }
}