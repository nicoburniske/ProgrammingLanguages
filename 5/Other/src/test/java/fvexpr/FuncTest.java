package fvexpr;

import answer.Answer;
import fdecl.SFVDecl;
import org.junit.Before;
import org.junit.Test;
import store.Location;
import store.Store;
import store.StoreUtils;

import java.util.Arrays;

import static fvexpr.Constants.CLOSURE_STRING;
import static org.junit.Assert.assertEquals;

public class FuncTest {
    Store<Var, Location> stdEnv = StoreUtils.initializeStd().get(0); // the "standard library"
    Store<Location, Answer> stdStore = StoreUtils.initializeStd().get(1);

    Func f1, f2, f3;
    Var x, y, z, a, b;
    DeclArray declArray1;


    @Before
    public void setUp() throws Exception {
        x = new Var("x");
        y = new Var("y");
        z = new Var("+");
        a = new Var("a");
        f1 = new Func(Arrays.asList(), new Int(1L));
        f2 = new Func(Arrays.asList(a,b), new Int(2L));
        f3 = new Func(Arrays.asList(), new Operator(a, b, z));
        declArray1 = new DeclArray(Arrays.asList(new SFVDecl(a, new Int(8L))), f3);

    }

    @Test
    public void name() {
        assertEquals(f1.interpret(stdEnv, stdStore).result, CLOSURE_STRING);
        assertEquals(f2.interpret(stdEnv, stdStore).result, CLOSURE_STRING);
        assertEquals(f3.interpret(stdEnv, stdStore).result, CLOSURE_STRING);
    }
}
