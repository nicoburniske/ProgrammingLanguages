package store;

import answer.Answer;
import answer.AnswerCell;
import answer.AnswerInt;
import fvexpr.Int;
import fvexpr.Operator;
import fvexpr.SFVExpr;
import fvexpr.Var;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class PreludeTests {
    Store<Var, Location> stdEnv = StoreUtils.initializeStd().get(0); // the "standard library"
    Store<Location, Answer> stdStore = StoreUtils.initializeStd().get(1); // the "standard library"

    /**
     * @ , which consumes one argument value, allocates a cell store location, sticks the given value into the store at this location this cell,
     *  and returns a cell. A cell value is distinct from both Ints and function values. Its only useful content is the newly allocated location.
     *
     * ! , which consumes one argument value—a cell—and retrieves the current value in the location specified by this cell.
     *
     * = , which consumes two argument values—a cell and an arbitrary value—sticks this second value into the location specified by this cell, and returns the value that used to be at this location.
     */
    @Test
    public void testNewPreludePrimops() {
        // test the @ operator
        SFVExpr useConstructor = new Operator(new Int((long) 55), Arrays.asList(), new Var("@"));
        AnswerCell allocatedCell = (AnswerCell) useConstructor.interpret(stdEnv, stdStore);
        AnswerInt valueStored = (AnswerInt) stdStore.get(allocatedCell.result.getLocation());
        assertEquals(new BigInteger("55"), valueStored.result);

        SFVExpr useGet = new Operator((allocatedCell.result), Arrays.asList(), new Var("!"));
        Answer valueRetrieved = useConstructor.interpret(stdEnv, stdStore);
//        assertEquals(new BigInteger("55"), valueRetrieved.result);
//
    }
}
