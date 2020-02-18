package tpal;

import env.IEnv;
import env.IEnvEnd;
import env.Tuple;
import org.junit.Test;
import tast.TASTVar;
import tast.star_ast.StarAST;
import type.Type;
import type.TypeFunction;
import type.TypeInt;

import java.util.Arrays;

import static utils.Constants.ERROR_UNDECLARED_VARIABLE_TEMPLATE;
import static org.junit.Assert.*;

public class TPALVarTest {

    @Test
    public void typeCheck() {
        TPALVar var = new TPALVar("a");
        try {
            Tuple varTuple = var.typeCheck(new IEnvEnd<>());
            fail();
        } catch (IllegalStateException e) {
            assertEquals(e.getMessage(), String.format(ERROR_UNDECLARED_VARIABLE_TEMPLATE, "a"));
        }
        IEnv<TPALVar, Type> env = new IEnvEnd<>();
        env = env.put(new TPALVar("a"), new TypeInt());
        Tuple varTuple = var.typeCheck(env);
        assertEquals(new StarAST(new TASTVar("a"), new TypeInt()), varTuple.getLeft());
        assertEquals(env, varTuple.getRight());

        TypeFunction tf = new TypeFunction(Arrays.asList(new TypeInt(), new TypeInt()), new TypeInt());
        env = env.put(new TPALVar("b"), tf);
        TPALVar varB = new TPALVar("b");
        Tuple varBTuple = varB.typeCheck(env);
        assertEquals(new StarAST(new TASTVar("b"), tf), varBTuple.getLeft());
        assertEquals(env, varBTuple.getRight());

    }
}