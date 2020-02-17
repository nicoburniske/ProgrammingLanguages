package tpal;

import env.IEnvEnd;
import env.Tuple;
import org.junit.Test;
import tast.TASTFunc;
import tast.TASTInteger;
import tast.star_ast.StarAST;
import type.TypeFunction;
import type.TypeInt;
import type.TypedVar;

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