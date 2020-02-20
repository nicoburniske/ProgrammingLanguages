package typecheck.tpal;

import typecheck.env.IEnvEnd;
import typecheck.env.Tuple;
import org.junit.Test;
import typecheck.tast.TASTInteger;
import typecheck.tast.star_ast.StarAST;
import typecheck.type.TypeInt;

import static org.junit.Assert.*;

public class TPALIntTest {

    @Test
    public void typeCheck() {
        TPALInt tint = new TPALInt(12);
        Tuple tintTuple = tint.typeCheck(new IEnvEnd<>());
        assertEquals(new StarAST(new TASTInteger(12), new TypeInt()), tintTuple.getLeft());
        assertEquals(new IEnvEnd<>(), tintTuple.getRight());
    }
}