package interpreter.pal;

import interpreter.utils.CPSUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ToyCallTest {

    ToyCall ex1;
    Toy result1;

    @Before
    public void init() {
        ex1 = new ToyCall(new ToyFunc(new ArrayList<>(), new ToyInt(100L)), new ArrayList<>());
        result1 = new ToyFunc(CPSUtils.KList, new ToyCall(new ToyFunc(CPSUtils.KList, new ToyCall(CPSUtils.K,
                new ToyFunc(CPSUtils.KList, new ToyCall(CPSUtils.K, new ToyInt(100L))))), new ToyFunc(Arrays.asList(new ToyVar("yeet")), new ToyCall( new ToyVar("yeet"), CPSUtils.K))));
    }
    @Test
    public void CPS() {
        assertTrue(Toy.alphaEquals(this.ex1.CPS(), this.result1));
    }
}