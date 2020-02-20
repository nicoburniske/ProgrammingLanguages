package typecheck.type;

import typecheck.env.IEnvEnd;
import typecheck.env.Tuple;
import org.junit.Test;
import typecheck.tast.TASTVar;
import typecheck.tast.star_ast.StarAST;
import typecheck.tpal.TPALVar;

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