package typechecker.type;

import common.LookupTableEnd;
import typechecker.env.Tuple;
import org.junit.Test;
import typechecker.tast.TASTVar;
import typechecker.tast.star_ast.StarAST;
import typechecker.tpal.TPALVar;

import static org.junit.Assert.*;

public class TypedVarTest {

    @Test
    public void typeCheck() {
        TypedVar var = new TypedVar("a", new TypeInt());
        Tuple varTuple = var.typeCheck(new LookupTableEnd<>());
        assertEquals(new StarAST(new TASTVar("a"), new TypeInt()), varTuple.getLeft());
        assertEquals(new LookupTableEnd<>().put(new TPALVar("a"), new TypeInt()), varTuple.getRight());
    }
}