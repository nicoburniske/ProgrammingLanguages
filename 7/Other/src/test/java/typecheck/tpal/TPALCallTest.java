package typecheck.tpal;

import typecheck.env.IEnv;
import typecheck.env.IEnvEnd;
import typecheck.env.Tuple;
import org.junit.Test;
import typecheck.tast.TASTFunc;
import typecheck.tast.TASTFuncCall;
import typecheck.tast.TASTInteger;
import typecheck.tast.star_ast.StarAST;
import typecheck.type.TypeFunction;
import typecheck.type.TypeInt;

import java.util.Arrays;

import static typecheck.utils.Constants.ERROR_ARGS_PARAMS_COUNT_DONT_MATCH;
import static typecheck.utils.Constants.ERROR_FUNCTION_EXPECTED;
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