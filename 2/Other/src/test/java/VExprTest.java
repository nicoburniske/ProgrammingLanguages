import org.junit.Before;
import org.junit.Test;
import sun.awt.X11.XSystemTrayPeer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class VExprTest {
    VExpr a, b, c;
    VExpr one, four, five, ten;
    VExpr aPlusB, aTimesC, bTimesATimesC, aPlus1, onePlus1;
    Decl aEquals1, bEquals4, cEquals5, aEquals10;
    VExpr declArray1, declArray2, declArray3, declArray1SDResponse, aTimesCReplaced;

    VExpr complicatedExample1, complicatedExample1SD, complicatedExample2, complicatedExample2SD;

    @Before
    public void setupSimple() {
        this.a = new Var("a");
        this.b = new Var("b");
        this.c = new Var("c");

        this.one = new VInt(1);
        this.four = new VInt(4);
        this.five = new VInt(5);
        this.ten = new VInt(10);

        this.aPlusB = new VOperator(this.a, this.b, "+");
        this.aTimesC = new VOperator(this.a, this.c, "*");
        this.aTimesCReplaced = new VOperator(new VarPair(0, 0), new VarPair(0, 2), "*");
        this.bTimesATimesC = new VOperator(this.b, this.aTimesC, "*");
        this.aPlus1 = new VOperator(this.a, new VInt(1), "+");
        this.onePlus1 = new VOperator(new VInt(1), new VInt(1), "+");

        this.aEquals1 = new Decl((Var) this.a, this.one);
        this.bEquals4 = new Decl((Var) this.b, this.four);
        this.cEquals5 = new Decl((Var) this.c, this.five);
        this.aEquals10 = new Decl((Var) this.a, this.ten);

        this.declArray1 = new VDeclArray(Arrays.asList(this.aEquals1, this.bEquals4, this.cEquals5), this.aTimesC);
        this.declArray2 = new VDeclArray(Arrays.asList(this.aEquals1, this.bEquals4, this.cEquals5), this.bTimesATimesC);
        this.declArray3 = new VDeclArray(Arrays.asList(this.aEquals10), this.declArray2);

        this.declArray1SDResponse = new VDeclArray(Arrays.asList(this.aEquals1, this.bEquals4, this.cEquals5), this.aTimesCReplaced);

    }

    @Before
    public void setUpComplicated() {
        //    Decl,        Decl,        Decl,         Scope
        // [ [x = 5] , [ x = x + 6], [ y = x * 5 ], [ x + y ] ]
        // y = 55 and x = 11 -> x + y = 66
        complicatedExample1 = new VDeclArray(Arrays.asList(new Decl(new Var("x"), new VInt(5)),
                new Decl(new Var("x"), new VOperator(new Var("x"), new VInt(6), "+")),
                new Decl(new Var("y"), new VOperator(new Var("x"), new VInt(5), "*"))),
                new VOperator(new Var("x"), new Var("y"), "+"));

        complicatedExample1SD = new VDeclArray(Arrays.asList(new Decl(new Var("x"), new VInt(5)),
                new Decl(new Var("x"), new VOperator(new VarPair(0, 0), new VInt(6), "+")),
                new Decl(new Var("y"), new VOperator(new VarPair(0, 1), new VInt(5), "*"))),
                new VOperator(new VarPair(0, 1), new VarPair(0, 2), "+"));

        /*
        [
            ["let","x","=",1],
            ["let","y","=",2],
            ["let", "m", "=", 100],
            [["let","m","=",4],["m", "*", "y"]], "*", "m"
        ]
            int inner_result = m * y where m = 4 and y = 2 = 8
            int outer_result = inner_result * m where m = 100 = 800
         */
        complicatedExample2 = new VDeclArray(Arrays.asList(
                new Decl(new Var("x"), new VInt(1)),
                new Decl(new Var("y"), new VInt(2)),
                new Decl(new Var("m"), new VInt(100))),
                new VOperator(
                        new VDeclArray(Arrays.asList(
                                new Decl(new Var("m"), new VInt(4))),
                                new VOperator(new Var("m"), new Var("y"), "*")),
                        new Var("m"), "*"));

        complicatedExample2SD = new VDeclArray(Arrays.asList(
                new Decl(new Var("x"), new VInt(1)),
                new Decl(new Var("y"), new VInt(2)),
                new Decl(new Var("m"), new VInt(100))),
                new VOperator(
                        new VDeclArray(Arrays.asList(
                                new Decl(new Var("m"), new VInt(4))),
                                new VOperator(new VarPair(0, 0), new VarPair(1, 1), "*")),
                        new VarPair(0, 2), "*"));
    }

    @Test
    public void testSD() {
        assertEquals(one, one.sd(new HashMap(), 0));
        assertEquals(onePlus1, onePlus1.sd(new HashMap(), 0));
        assertEquals(declArray1SDResponse, declArray1.sd(new HashMap(), 0));
        assertEquals(complicatedExample1.sd(new HashMap<String, Stack<AccumulatorType>>(), 0), complicatedExample1SD);
        assertEquals(complicatedExample2.sd(new HashMap<String, Stack<AccumulatorType>>(), 0), complicatedExample2SD);
    }

    @Test
    public void testInterpreter() {
        assertEquals(1, this.one.evaluate());
        assertEquals(2, this.onePlus1.evaluate());
        assertEquals(5, this.declArray1.evaluate());
        assertEquals(20, this.declArray2.evaluate());
        assertEquals(20, this.declArray3.evaluate());
        assertEquals(66, complicatedExample1.evaluate());
        assertEquals(800, complicatedExample2.evaluate());
    }

    @Test(expected = IllegalStateException.class)
    public void testExceptionInterpreterVar() {
        this.a.evaluate();
    }

    @Test(expected = IllegalStateException.class)
    public void testExceptionInterpreterVarPair() {
        new VarPair(1, 2).evaluate();
    }

    @Test
    public void testSubstitute() {
        VExpr complicatedExample1Substitute = new VDeclArray(Arrays.asList(
                new Decl(new Var("x"), new VOperator(new Var("x"), new VInt(6), "+")),
                new Decl(new Var("y"), new VOperator(new Var("x"), new VInt(5), "*"))),
                new VOperator(new Var("x"), new Var("y"), "+"));
        VExpr complicatedExample1SubstituteSolution = new VDeclArray(Arrays.asList(
                new Decl(new Var("x"), new VOperator(new VInt(5), new VInt(6), "+")),
                new Decl(new Var("y"), new VOperator(new VOperator(new VInt(5), new VInt(6), "+"), new VInt(5), "*"))),
                new VOperator(new VOperator(new VInt(5), new VInt(6), "+"), new VOperator(new VOperator(new VInt(5), new VInt(6), "+"), new VInt(5), "*"), "+"));

        Map<String, Stack<VExpr>> mapX = new HashMap<String, Stack<VExpr>>();
        mapX.put("x", new Stack<VExpr>());
        mapX.get("x").push( new VInt(5));
        assertEquals(complicatedExample1Substitute.substitute(mapX), complicatedExample1SubstituteSolution);
    }
}