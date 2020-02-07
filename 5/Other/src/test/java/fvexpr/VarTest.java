package fvexpr;

import answer.Answer;
import fdecl.SFVDecl;
import org.junit.Before;
import org.junit.Test;
import store.Location;
import store.Store;
import store.StoreUtils;

import java.math.BigInteger;
import java.util.Arrays;

import static fvexpr.Constants.ERROR_UNDECLARED_VARIABLE_TEMPLATE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class VarTest {
    Var x, y, z, a, b;
    DeclArray declArray1;
    SFVDecl declx, decly, declz;
    Store<Var, Location> stdEnv = StoreUtils.initializeStd().get(0); // the "standard library"
    Store<Location, Answer> stdStore = StoreUtils.initializeStd().get(1);

    @Before
    public void setUp() throws Exception {
        x = new Var("x");
        y = new Var("y");
        z = new Var("+");
        a = new Var("a");
        declx = new SFVDecl(x, new Int(23L));
        decly = new SFVDecl(y, new Int(5L));
        declz = new SFVDecl(a, new Operator(x, Arrays.asList(y), z));
        declArray1 = new DeclArray(Arrays.asList(declx, decly, declz), new Operator(y, Arrays.asList(a), z));

    }

    @Test
    public void interpretSuccess() {
       assertEquals(declArray1.interpret(stdEnv, stdStore).result, new BigInteger("33"));
    }

    @Test
    public void interpretFailTest() {
        try {
            x.interpret(new Store(), new Store());
            fail();
        } catch (Exception e) {
            assertEquals(String.format(ERROR_UNDECLARED_VARIABLE_TEMPLATE, "x"), e.getMessage());
        }
        try {
            y.interpret(new Store(), new Store());
            fail();
        } catch (Exception e) {
            assertEquals(String.format(ERROR_UNDECLARED_VARIABLE_TEMPLATE, "y"), e.getMessage());
        }
        try {
            z.interpret(new Store(), new Store());
            fail();
        } catch (Exception e) {
            assertEquals(String.format(ERROR_UNDECLARED_VARIABLE_TEMPLATE, "+"), e.getMessage());
        }
    }
}
