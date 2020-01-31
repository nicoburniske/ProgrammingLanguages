import fdecl.FDeclInt;
import fvexpr.FVExpr;
import fvexpr.Int;
import fvexpr.Var;
import org.junit.Before;
import org.junit.Test;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import static org.junit.Assert.*;

public class MainTest {
    FVExpr decl1, decl2, decl3;
    FVExpr declArr1, DeclArr2;

    @Before
    public void init() {
        this.decl1 = (FVExpr) new FDeclInt(new Var("x"), new Int((long) 5));
    }
    @Test
    public void parseSVexp() {

    }

    @Test
    public void testToJSON() {
        System.out.println(this.decl1.toJson());
    }
}