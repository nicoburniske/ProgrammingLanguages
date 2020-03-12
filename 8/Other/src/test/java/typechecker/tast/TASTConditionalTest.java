package typechecker.tast;

import org.junit.Test;
import typechecker.tast.star_ast.StarAST;
import typechecker.tpal.TPALConditional;
import typechecker.tpal.TPALInt;
import typechecker.type.TypeInt;

import static org.junit.Assert.*;

public class TASTConditionalTest {

    @Test
    public void toJSONString() {
        TASTConditional cond = new TASTConditional(new StarAST(new TASTVar("a"),new TypeInt()),new StarAST(new TASTVar("b"),new TypeInt()), new StarAST(new TASTVar("c"),new TypeInt()));
        assertEquals("[\"if-0\",{\"expr\":\"a\",\"type\":\"int\"},{\"expr\":\"b\",\"type\":\"int\"},{\"expr\":\"c\",\"type\":\"int\"}]",cond.toJSONString());
    }

    @Test
    public void toJava() {
        TASTConditional conditional = new TASTConditional(new StarAST(new TASTInteger(1), new TypeInt()), new StarAST(new TASTInteger(1), new TypeInt()), new StarAST(new TASTInteger(2), new TypeInt()));
        assertEquals("new MyInteger(1).equals(new MyInteger(0)) ? new MyInteger(1) : new MyInteger(2)", conditional.toJava(new TypeInt()));
    }
}