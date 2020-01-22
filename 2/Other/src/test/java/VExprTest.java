import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class VExprTest {
    VExpr a, b, c;
    VExpr one, four, five, ten;
    VExpr aPlusB, aTimesC, bTimesATimesC, aPlus1, onePlus1;
    Decl aEquals1, bEquals4, cEquals5, aEquals10;
    VExpr declArray1, declArray2, declArray3, declArray1SDResponse, aTimesCReplaced;

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
        this.aTimesCReplaced = new VOperator(new VarPair(0,0), new VarPair(0, 2), "*");
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

        this.declArray1SDResponse = new VDeclArray(Arrays.asList(this.aEquals1, this.bEquals4, this.cEquals5), this.aTimesCReplaced);

    }


    @Test
    public void testSD() {
        assertEquals(one, one.sd(new HashMap(), 0));
        assertEquals(onePlus1, onePlus1.sd(new HashMap(), 0));
        assertEquals(declArray1SDResponse, declArray1.sd(new HashMap(), 0));
    }

    @Test public void testInterpreter() {
        assertEquals(1, this.one.evaluate());
        assertEquals(2, this.onePlus1.evaluate());
        assertEquals(5, this.declArray1.evaluate());
        assertEquals(20, this.declArray2.evaluate());
        assertEquals(20, this.declArray3.evaluate());


        //    Decl          Decl        Decl          Scope
        // [ [x = 5] , [ x = x + 6], [ y = x * 5 ], [ x + y ] ]
        // y = 55 and x = 11 -> x + y = 66
        VExpr complicatedExample = new VDeclArray(Arrays.asList(new Decl(new Var("x"), new VInt(5)),
                new Decl(new Var("x"), new VOperator(new Var("x"), new VInt(6), "+")),
                new Decl(new Var("y"), new VOperator(new Var("x"), new VInt(5), "*"))),
                new VOperator(new Var("x"), new Var("y"), "+"));

        assertEquals(66, complicatedExample.evaluate());
    }
}