package typechecker.tpal;

import common.LookupTable;
import common.LookupTableEnd;
import typechecker.env.Tuple;
import org.junit.Test;
import typechecker.tast.TASTFunc;
import typechecker.tast.TASTFuncCall;
import typechecker.tast.TASTInteger;
import typechecker.tast.TASTVar;
import typechecker.tast.star_ast.StarAST;
import typechecker.type.TypeFunction;
import typechecker.type.TypeInt;
import typechecker.type.TypedVar;
import typechecker.utils.StandardLib;

import java.util.Arrays;

import static typechecker.utils.Constants.ERROR_ARGS_PARAMS_COUNT_DONT_MATCH;
import static typechecker.utils.Constants.ERROR_FUNCTION_EXPECTED;
import static org.junit.Assert.*;

public class TPALCallTest {

    @Test
    public void typeCheck() {
        TPALCall tpalCall = new TPALCall(new TPALVar("a"), Arrays.asList());
        LookupTable env = new LookupTableEnd<>();
        env = env.put(new TPALVar("a"), new TypeInt());
        try {
            tpalCall.typeCheck(env);
            fail();
        } catch (IllegalStateException e) {
            assertEquals(ERROR_FUNCTION_EXPECTED, e.getMessage());
        }
        env = env.put(new TPALVar("a"), new TypeFunction(Arrays.asList(new TypeInt()), new TypeInt()));
        try {
            tpalCall.typeCheck(env);
            fail();
        } catch (IllegalStateException e) {
            assertEquals(ERROR_ARGS_PARAMS_COUNT_DONT_MATCH, e.getMessage());
        }
        TPALCall tpalCall2 = new TPALCall(new TPALFunc(Arrays.asList(), new TPALInt(1)), Arrays.asList());
        Tuple callTup = tpalCall2.typeCheck(env);
        assertEquals(
                new StarAST(
                        new TASTFuncCall(
                                new StarAST(
                                        new TASTFunc(
                                                Arrays.asList(),
                                                new StarAST(new TASTInteger(1), new TypeInt())),
                                        new TypeFunction(Arrays.asList(), new TypeInt())),
                                Arrays.asList()),
                        new TypeInt()),
                callTup.getLeft());
        assertEquals(env, callTup.getRight());

        assertEquals(new Tuple(
                new StarAST(
                        new TASTFuncCall(
                                new StarAST(
                                        new TASTVar("+"),
                                        new TypeFunction(
                                                Arrays.asList(new TypeInt(), new TypeInt()), new TypeInt())),
                                Arrays.asList(
                                        new StarAST(
                                                new TASTInteger(1),
                                                new TypeInt()),
                                        new StarAST(new TASTInteger(2), new TypeInt()))), new TypeInt()), StandardLib.stdLib()),
                (new TPALCall(new TPALVar("+"), Arrays.asList(new TPALInt(1L), new TPALInt(2L)))).typeCheck(StandardLib.stdLib()));


    }


    @Test
    public void toJava() {
        TPALFunc func = new TPALFunc(Arrays.asList(new TypedVar("a", new TypeInt())), new TPALInt(1));
        assertEquals("((Function<Integer,Integer>)(Integer a) -> 1).apply(3)",new TPALCall(func, Arrays.asList(new TPALInt(3l))).toJava());
    }
}