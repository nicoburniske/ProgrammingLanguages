package fvexpr;

import fdecl.SFVDecl;
import org.junit.Before;
import org.junit.Test;

public class DeclArrayTest {
    DeclArray declArray1, declArray2, declArray3;
    SFVDecl declx, decly, declz;
    Var x, y, z;

    @Before
    public void setUp() throws Exception {
        x = new Var("x");
        y = new Var("y");
        z = new Var("z");
        declx = new SFVDecl(x, new Int(9L));
    }

    @Test
    public void interpret() {
    }
}
