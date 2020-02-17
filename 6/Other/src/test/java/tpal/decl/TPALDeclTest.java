package tpal.decl;

import env.IEnvEnd;
import env.TupleGeneric;
import org.junit.Test;
import tast.TASTVar;
import tast.star_ast.StarAST;
import tast.star_decl.StarDecl;
import tpal.TPALVar;
import type.TypeFunction;
import type.TypeInt;
import type.TypedVar;

import java.util.Arrays;

import static utils.Constants.ERROR_DECL_TYPE_MATCHING;
import static org.junit.Assert.*;

public class TPALDeclTest {

    @Test
    public void typeCheck() {
        TPALDecl decl = new TPALDecl(new TypedVar("a", new TypeInt()), new TypedVar("b", new TypeFunction(Arrays.asList(new TypeInt()), new TypeInt())));
        try {
            TupleGeneric declTuple = decl.typeCheck(new IEnvEnd<>());
            fail();
        } catch (IllegalStateException e) {
            assertEquals(ERROR_DECL_TYPE_MATCHING, e.getMessage());
        }
        TPALDecl decl2 = new TPALDecl(new TypedVar("a", new TypeFunction(Arrays.asList(new TypeInt()), new TypeInt())), new TypedVar("b", new TypeInt()));
        try {
            TupleGeneric declTuple = decl.typeCheck(new IEnvEnd<>());
            fail();
        } catch (IllegalStateException e) {
            assertEquals(ERROR_DECL_TYPE_MATCHING, e.getMessage());
        }


        TPALDecl decl3 = new TPALDecl(new TypedVar("a", new TypeInt()), new TypedVar("b",new TypeInt()));
        TupleGeneric decl3Tup = decl3.typeCheck(new IEnvEnd());
        assertEquals(new StarDecl(new TypedVar("a", new TypeInt()),new StarAST(new TASTVar("b"), new TypeInt())), decl3Tup.getLeft());
        assertEquals(new IEnvEnd<>().put(new TPALVar("a"), new TypeInt()), decl3Tup.getRight());



    }
}