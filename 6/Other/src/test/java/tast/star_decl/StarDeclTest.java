package tast.star_decl;


import org.junit.Before;
import org.junit.Test;
import tast.TASTVar;
import tast.star_ast.StarAST;
import type.TypeFunction;
import type.TypeInt;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

class StarDeclTest {

    @Before
    void setUp() {
    }

    @Test
    void toJSONString() {
        StarDecl starDecl  = new StarDecl(new TASTVar("dec"),new StarAST(new TASTVar("les"),new TypeFunction(Arrays.asList(new TypeInt(), new TypeInt()), new TypeInt())));
        assertEquals("", starDecl.toJSONString());
    }
}