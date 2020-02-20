package typechecker.tpal;

import typechecker.env.IEnvEnd;
import typechecker.env.Tuple;
import org.junit.Test;
import typechecker.tast.TASTFunc;
import typechecker.tast.TASTInteger;
import typechecker.tast.star_ast.StarAST;
import typechecker.type.TypeFunction;
import typechecker.type.TypeInt;
import typechecker.type.TypedVar;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TPALFuncTest {

    @Test
    public void typeCheck() {
        TPALFunc blackjack = new TPALFunc(Arrays.asList(new TypedVar("a", new TypeInt())),new TPALInt(21));
        Tuple backTuple = blackjack.typeCheck(new IEnvEnd<>());
        assertEquals(new StarAST(
                new TASTFunc(
                        Arrays.asList(new TypedVar("a", new TypeInt())),
                        new StarAST(new TASTInteger(21), new TypeInt())),
                new TypeFunction(Arrays.asList(new TypeInt()), new TypeInt())), backTuple.getLeft());
        assertEquals(new IEnvEnd<>(), backTuple.getRight());

    }
}