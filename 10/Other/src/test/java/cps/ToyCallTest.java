package cps;

import interpreter.pal.*;
import interpreter.utils.CPSUtils;
import interpreter.utils.EnvStoreTuple;
import interpreter.utils.ValueEnvStoreTuple;
import interpreter.value.Cell;
import interpreter.value.ValueCell;
import interpreter.value.ValueClosure;
import interpreter.value.ValueInt;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.EventListener;

import static interpreter.utils.RuntimeExceptions.*;
import static org.junit.Assert.*;

public class ToyCallTest {
    ToyCall ex1, ex2, allocate, get, set, fib10, primopError, funcExpected, argsParamsCount;
    ToyFunc inputInput, fib;
    ToyDeclArray declarr;

    @Before
    public void setUp() throws Exception {
        ex1 = new ToyCall(new ToyFunc(Arrays.asList(new ToyVar("input")), new ToyVar("input")), new ToyInt(4L));
        ex2 = new ToyCall(new ToyVar("+"), Arrays.asList(new ToyInt(5L), new ToyInt(100L)));
        inputInput = new ToyFunc(Arrays.asList(new ToyVar("input")), new ToyVar("input"));
        allocate = new ToyCall(new ToyVar("@"), new ToyInt(2L));
        get = new ToyCall(new ToyVar("!"), allocate);
        set = new ToyCall(new ToyVar("="), Arrays.asList(allocate, new ToyInt(78L)));
        fib = new ToyFunc(Arrays.asList(new ToyVar("ii")), new ToyConditional(new ToyVar("ii"), new ToyInt(0L), new ToyCall(new ToyVar("+"),
                Arrays.asList(new ToyVar("ii"), new ToyCall(new ToyVar("fib"), new ToyCall(new ToyVar("+"), Arrays.asList(new ToyInt(-1L), new ToyVar("ii"))))))));
        declarr = new ToyDeclArray(Arrays.asList(new Decl(new ToyVar("fib"), this.fib)), new ToyCall(new ToyVar("fib"), new ToyInt(13L)));

        primopError = new ToyCall(new ToyVar("^"), Arrays.asList(new ToyInt(3L), new ToyInt(-4L)));
        funcExpected = new ToyCall(new ToyInt(4L), Arrays.asList(new ToyInt(3L), new ToyInt(-4L)));
        argsParamsCount = new ToyCall(new ToyVar("+"), Arrays.asList(new ToyInt(3L), new ToyInt(-4L), new ToyInt(5L)));
    }

    public int sum(int curr) {
        if (curr == 0) {
            return 0;
        } else {
            return curr + sum(curr - 1);
        }
    }

    @Test
    public void CPS() {
        assertEquals(new ValueInt(4L), CPSUtils.toTestFormat(ex1.CPS()).interpret(EnvStoreTuple.stdLib()).getLeft());
        assertEquals(new ValueInt(105L), CPSUtils.toTestFormat(ex2.CPS()).interpret(EnvStoreTuple.stdLib()).getLeft());
        assertEquals(new ValueInt(2L), CPSUtils.toTestFormat(get.CPS()).interpret(EnvStoreTuple.stdLib()).getLeft());
        assertEquals(new ValueInt(2L), CPSUtils.toTestFormat(set.CPS()).interpret(EnvStoreTuple.stdLib()).getLeft());
        ValueEnvStoreTuple result = CPSUtils.toTestFormat(declarr.CPS()).interpret(EnvStoreTuple.stdLib());
        assertEquals(new ValueInt(this.sum(13)).toJSONString(), result.getLeft().toJSONString());
    }

    @Test
    public void testPrimopDomain() {
        try {
            primopError.run();
            fail();
        } catch (Exception e) {
            assertEquals(ARITHMETIC_ERROR, e.getMessage());
        }
    }

    @Test
    public void testFuncExpectedError() {
        try {
            funcExpected.run();
            fail();
        } catch (Exception e) {
            assertEquals(ERROR_FUNCTION_EXPECTED, e.getMessage());
        }
    }


    @Test
    public void testFuncArgsParamsCount() {
        try {
            argsParamsCount.run();
            fail();
        } catch (Exception e) {
            assertEquals(ERROR_ARGS_PARAMS_COUNT_DONT_MATCH, e.getMessage());
        }
    }
}