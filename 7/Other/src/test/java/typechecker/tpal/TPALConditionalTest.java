package typechecker.tpal;

import common.LookupTable;
import common.LookupTableEnd;
import typechecker.env.Tuple;
import org.junit.Test;
import typechecker.tast.TASTConditional;
import typechecker.tast.TASTInteger;
import typechecker.tast.TASTVar;
import typechecker.tast.star_ast.StarAST;
import typechecker.type.Type;
import typechecker.type.TypeFunction;
import typechecker.type.TypeInt;
import typechecker.type.TypedVar;

import java.util.Arrays;

import static typechecker.utils.Constants.ERROR_COND_TYPE_ERROR;
import static typechecker.utils.Constants.ERROR_INT_EXPECTED;
import static org.junit.Assert.*;

public class TPALConditionalTest {

    @Test
    public void typeCheck() {
        TPALConditional conditional = new TPALConditional(new TPALVar("a"), new TPALInt(1), new TPALInt(2));
        LookupTable<TPALVar, Type> env = new LookupTableEnd();
        env = env.put(new TPALVar("a"), new TypeFunction(Arrays.asList(new TypeInt()), new TypeInt()));
        try {
            Tuple condTup = conditional.typeCheck(env);
            fail();
        } catch (IllegalStateException e) {
            assertEquals(e.getMessage(), ERROR_INT_EXPECTED);
        }
        env = env.put(new TPALVar("a"), new TypeInt());
        Tuple condTup = conditional.typeCheck(env);
        assertEquals(new StarAST(new TASTConditional(
                new StarAST(new TASTVar("a"), new TypeInt()),
                new StarAST(new TASTInteger(1), new TypeInt()),
                new StarAST(new TASTInteger(2), new TypeInt())), new TypeInt()), condTup.getLeft());
        assertEquals(env, condTup.getRight());

        TPALConditional conditional2 = new TPALConditional(new TPALVar("a"), new TypedVar("b", new TypeFunction(Arrays.asList(new TypeInt()), new TypeInt())), new TPALInt(2));
        try {
            Tuple condTup2 = conditional2.typeCheck(env);
            fail();
        } catch (IllegalStateException e) {
            assertEquals(e.getMessage(), ERROR_COND_TYPE_ERROR);
        }
    }
}