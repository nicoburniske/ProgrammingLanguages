package typecheck.tast.star_decl;


import org.junit.Before;
import org.junit.Test;
import typecheck.tast.TASTVar;
import typecheck.tast.star_ast.StarAST;
import typecheck.type.TypedVar;
import typecheck.type.TypeFunction;
import typecheck.type.TypeInt;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class StarDeclTest {

    @Before
    public void setUp() {
    }

    @Test
    public void toJSONString() {
        StarDecl starDecl  = new StarDecl(new TypedVar("dec", new TypeInt()),new StarAST(new TASTVar("les"),new TypeFunction(Arrays.asList(new TypeInt(), new TypeInt()), new TypeInt())));
        assertEquals("[\"let\",[\"dec\",\":\",\"int\"],\"=\",{\"expr\":\"les\",\"type\":[\"int\",\"int\",\"->\",\"int\"]}]", starDecl.toJSONString());
    }
}