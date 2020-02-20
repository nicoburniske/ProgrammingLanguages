package typechecker.tpal;

import typechecker.env.IEnv;
import typechecker.env.IEnvEnd;
import typechecker.env.Tuple;
import org.junit.Test;
import typechecker.tast.TASTFunc;
import typechecker.tast.TASTFuncCall;
import typechecker.tast.TASTInteger;
import typechecker.tast.star_ast.StarAST;
import typechecker.type.TypeFunction;
import typechecker.type.TypeInt;

import java.util.Arrays;

import static typechecker.utils.Constants.ERROR_ARGS_PARAMS_COUNT_DONT_MATCH;
import static typechecker.utils.Constants.ERROR_FUNCTION_EXPECTED;
import static org.junit.Assert.*;

public class TPALCallTest {

    @Test
    public void typeCheck() {
        TPALCall tpalCall = new TPALCall(new TPALVar("a"), Arrays.asList());
        IEnv env = new IEnvEnd<>();
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


    }


}