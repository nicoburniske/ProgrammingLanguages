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
        //[
        //  ["let", "x", "=", 0],
        //  ["let", "retx", "=", ["fun*", [], ["call", "retx"]],
        //  ["call", "retx"]
        //  ]
        //]
        assertEquals(new BigInteger("55"), declAndCallRecusiveFunc.interpret(stdEnv,stdStore).result);
    }

    @Test
    public void baconExampleTest() {
        //[
        //  ["let", "x", "=", 0],
        //  ["let", "retx", "=", ["fun*", [], "x"]],
        //  [
        //    ["let", "x", "=", 5],
        //    ["call", "retx"]
        //  ]
        //]
        SFVExpr test2 = new DeclArray(Arrays.asList(new SFVDecl(new Var("x"), new Int(0L)), new SFVDecl(new Var("retx"), new Func(Arrays.asList(), new Var("x")))),
                new DeclArray(Arrays.asList(new SFVDecl(new Var("x"), new Int(5L))),
                        new FuncCall(new Var("retx"), Arrays.asList())));
        //assertEquals(new BigInteger("0"), test2.interpret(stdEnv, stdStore).result);
    }

}
