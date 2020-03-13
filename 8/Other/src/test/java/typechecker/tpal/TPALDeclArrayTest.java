package typechecker.tpal;

import common.LookupTableEnd;
import typechecker.env.Tuple;
import org.junit.Test;
import typechecker.tast.TASTDeclArray;
import typechecker.tast.TASTInteger;
import typechecker.tast.TASTVar;
import typechecker.tast.star_ast.StarAST;
import typechecker.tast.star_decl.StarDecl;
import typechecker.tpal.decl.TPALDecl;
import typechecker.type.TypeInt;
import typechecker.type.TypedVar;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TPALDeclArrayTest {

    @Test
    public void typeCheck() {
        TPALDecl decl1 = new TPALDecl(new TypedVar("a", new TypeInt()), new TPALInt(1));
        TPALDecl decl2 = new TPALDecl(new TypedVar("b", new TypeInt()), new TPALInt(2));
        TPALDecl decl3 = new TPALDecl(new TypedVar("c", new TypeInt()), new TPALInt(3));
        TPALDeclArray declArray = new TPALDeclArray(Arrays.asList(decl1, decl2, decl3), new TPALVar("a"));
        Tuple declArrTup = declArray.typeCheck(new LookupTableEnd<>());
        StarDecl sDecl1 = new StarDecl(new TypedVar("a", new TypeInt()), new StarAST(new TASTInteger(1), new TypeInt()));
        StarDecl sDecl2 = new StarDecl(new TypedVar("b", new TypeInt()), new StarAST(new TASTInteger(2), new TypeInt()));
        StarDecl sDecl3 = new StarDecl(new TypedVar("c", new TypeInt()), new StarAST(new TASTInteger(3), new TypeInt()));
        TASTDeclArray tastDeclArray = new TASTDeclArray(Arrays.asList(sDecl1, sDecl2, sDecl3), new StarAST(new TASTVar("a"), new TypeInt()));
        assertEquals(new StarAST(tastDeclArray, new TypeInt()), declArrTup.getLeft());
        assertEquals(new LookupTableEnd<>(), declArrTup.getRight());
    }
}