package typechecker.tpal;

import common.LookupTableEnd;
import typechecker.env.Tuple;
import org.junit.Test;
import typechecker.tast.TASTInteger;
import typechecker.tast.star_ast.StarAST;
import typechecker.type.TypeInt;

import static org.junit.Assert.*;

public class TPALIntTest {

    @Test
    public void typeCheck() {
        TPALInt tint = new TPALInt(12);
        Tuple tintTuple = tint.typeCheck(new LookupTableEnd<>());
        assertEquals(new StarAST(new TASTInteger(12), new TypeInt()), tintTuple.getLeft());
        assertEquals(new LookupTableEnd<>(), tintTuple.getRight());
    }
}