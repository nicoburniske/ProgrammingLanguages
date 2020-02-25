package typechecker.tpal;

import common.LookupTable;
import common.LookupTableEnd;
import typechecker.env.Tuple;
import org.junit.Test;
import typechecker.tast.TASTVar;
import typechecker.tast.star_ast.StarAST;
import typechecker.type.Type;
import typechecker.type.TypeFunction;
import typechecker.type.TypeInt;

import java.util.Arrays;

import static typechecker.utils.Constants.ERROR_UNDECLARED_VARIABLE_TEMPLATE;
import static org.junit.Assert.*;

public class TPALVarTest {

    @Test
    public void typeCheck() {
        TPALVar var = new TPALVar("a");
        try {
            Tuple varTuple = var.typeCheck(new LookupTableEnd<>());
            fail();
        } catch (IllegalStateException e) {
            assertEquals(e.getMessage(), String.format(ERROR_UNDECLARED_VARIABLE_TEMPLATE, "a"));
        }
        LookupTable<TPALVar, Type> env = new LookupTableEnd<>();
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