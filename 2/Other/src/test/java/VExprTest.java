import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class VExprTest {
    VExpr a, b, c;
    VExpr one, four, five, ten;
    VExpr aPlusB, aTimesC, bTimesATimesC, aPlus1, onePlus1;
    Decl aEquals1, bEquals4, cEquals5, aEquals10;
    VExpr declArray1, declArray2, declArray3;

    @Before
    public void setup() {
        this.a = new Var("a");
        this.b = new Var("b");
        this.c = new Var("c");

        this.one = new VInt (1);
        this.four = new VInt(4);
        this.five = new VInt(5);
        this.ten = new VInt(10);


        this.aPlusB = new VOperator(this.a, this.b, "+");
        this.aTimesC = new VOperator(this.a, this.c, "*");
        this.bTimesATimesC = new VOperator(this.b, this.aTimesC, "*");
        this.aPlus1 = new VOperator(this.a, new VInt(1), "+");
        this.onePlus1 = new VOperator(new VInt(1), new VInt(1), "+");

        this.aEquals1 = new Decl((Var)this.a, this.one);
        this.bEquals4 = new Decl((Var)this.b, this.four);
        this.cEquals5 = new Decl((Var)this.c, this.five);
        this.aEquals10 = new Decl((Var)this.a, this.ten);


        this.declArray1 = new VDeclArray(Arrays.asList(this.aEquals1, this.bEquals4, this.cEquals5), this.aTimesC);
        this.declArray2 = new VDeclArray(Arrays.asList(this.aEquals1, this.bEquals4, this.cEquals5), this.bTimesATimesC);
        this.declArray3 = new VDeclArray(Arrays.asList(this.aEquals10), this.declArray2);
    }


    @Test
    public void testSD() {

    }

    @Test public void testInterpreter() {
        assertEquals(1, this.one.evaluate(new HashMap<String, Stack<Integer>>()));
        assertEquals(2, this.onePlus1.evaluate(new HashMap<String, Stack<Integer>>()));
        assertEquals(5, this.declArray1.evaluate(new HashMap<String, Stack<Integer>>()));
        assertEquals(20, this.declArray2.evaluate(new HashMap<String, Stack<Integer>>()));
        assertEquals(20, this.declArray3.evaluate(new HashMap<String, Stack<Integer>>()));
    }
}