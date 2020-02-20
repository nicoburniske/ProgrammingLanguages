package type;

import env.IEnvEnd;
import env.Tuple;
import org.junit.Test;
import tast.TASTVar;
import tast.star_ast.StarAST;
import tpal.TPALVar;

import static org.junit.Assert.*;

public class TypedVarTest {

    @Test
    public void typeCheck() {
        TypedVar var = new TypedVar("a", new TypeInt());
        Tuple varTuple = var.typeCheck(new IEnvEnd<>());
        assertEquals(new StarAST(new TASTVar("a"), new TypeInt()), varTuple.getLeft());
        assertEquals(new IEnvEnd<>().put(new TPALVar("a"), new TypeInt()), varTuple.getRight());
    }
}