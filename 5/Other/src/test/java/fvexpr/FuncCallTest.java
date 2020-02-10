package fvexpr;

import answer.Answer;
import fdecl.SFVDecl;
import org.junit.Test;
import store.Location;
import store.Store;
import store.StoreUtils;

import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class FuncCallTest {
    Store<Var, Location> stdEnv = StoreUtils.initializeStd().get(0); // the "standard library"
    Store<Location, Answer> stdStore = StoreUtils.initializeStd().get(1); // the "standard library"
    SFVExpr recursiveFunc = new Func(Arrays.asList(new Var("avar")),
            new Conditional(new Var("avar"),
                    new Int((long) 55),
                    new FuncCall(new Var("funx"), Arrays.asList(new Operator(new Var("avar"), new Int((long) -1), new Var("+"))))));
    SFVExpr declAndCallRecusiveFunc = new DeclArray(Arrays.asList(new SFVDecl(new Var("funx"), recursiveFunc)), new FuncCall(new Var("funx"), Arrays.asList(new Int((long) 5))));
    @Test
    public void testRecursiveFuncCall() {
        assertEquals(new BigInteger("55"), declAndCallRecusiveFunc.interpret(stdEnv,stdStore).result);
    }

}
