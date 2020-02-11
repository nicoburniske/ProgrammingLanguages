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

import static fvexpr.Constants.*;
import static org.junit.Assert.assertEquals;

public class OperatorTest {
    Var x, y, z, a, b, e;
    DeclArray declArray1, declArray2,declArray3, declArray4,declArray5;
    SFVDecl declx, decly, declz, declb;
    Store<Var, Location> stdEnv = StoreUtils.initializeStd().get(0); // the "standard library"
    Store<Location, Answer> stdStore = StoreUtils.initializeStd().get(1);
    Operator one,two, three, times, exp;

    @Before
    public void setUp() throws Exception {
        x = new Var("x");
        y = new Var("y");
        z = new Var("+");
        b = new Var("^");
        e = new Var("*");
        a = new Var("a");
        declx = new SFVDecl(x, new Int(23L));
        decly = new SFVDecl(y, new Int(5L));
        declz = new SFVDecl(a, new Operator(x, Arrays.asList(y), z));
        declb = new SFVDecl(b, new Func(Arrays.asList(), y));
        declArray1 = new DeclArray(Arrays.asList(declx, decly, declz), new Operator(y, Arrays.asList(a), e));
        one = new Operator(x, y, e);
        two = new Operator(y, b, e);
        three = new Operator(x, b, e);
        declArray2 = new DeclArray(Arrays.asList(decly), one);
        declArray3 = new DeclArray(Arrays.asList(decly, declb), two);
        declArray4 = new DeclArray(Arrays.asList(decly, declb), two);
        declArray5 = new DeclArray(Arrays.asList(decly, declb), three);
    }

    @Test
    public void interpret() {
        assertEquals(declArray1.interpret(stdEnv, stdStore).result, new BigInteger("140"));
        assertEquals(new BigInteger("0"), new Operator(new Int((long)1), Arrays.asList(new Int((long)-1)),new Var("+")).interpret(stdEnv, stdStore).result);
        assertEquals(new BigInteger("0"), new Operator(new Int((long)-1), Arrays.asList(new Int((long)1)),new Var("+")).interpret(stdEnv, stdStore).result);
    }

    @Test
    public void interpretErrorOrder() {
        try {
            one.interpret(stdEnv, stdStore);
        } catch (Exception e) {
            assertEquals(e.getMessage(), String.format(ERROR_UNDECLARED_VARIABLE_TEMPLATE, "y"));
        }
        try {
            declArray2.interpret(stdEnv, stdStore);
        } catch (Exception e) {
            assertEquals(e.getMessage(), String.format(ERROR_UNDECLARED_VARIABLE_TEMPLATE, "x"));
        }
        try {
            declArray3.interpret(stdEnv, stdStore);
        } catch (Exception e) {
            assertEquals(e.getMessage(), ERROR_INVALID_ARITHMETIC);
        }
        try {
            declArray4.interpret(new Store<>(), new Store<>());
        } catch (Exception e) {
            assertEquals(e.getMessage(), String.format(ERROR_UNDECLARED_VARIABLE_TEMPLATE, "*"));
        }
        try {
            declArray5.interpret(new Store<>(), new Store<>());
        } catch (Exception e) {
            assertEquals(e.getMessage(), String.format(ERROR_UNDECLARED_VARIABLE_TEMPLATE, "*"));
        }
    }

    @Test
    public void negativeExponent() {
        //TODO:Negative Exponent error checking
    }
}
