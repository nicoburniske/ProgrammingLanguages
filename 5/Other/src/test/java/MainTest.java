import answer.Answer;
import fdecl.SFVDecl;
import fvexpr.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import parser.ParseUtils;
import store.Location;
import store.Store;
import store.StoreUtils;

import java.io.FileReader;
import java.math.BigInteger;
import java.util.Arrays;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.fail;


public class MainTest {
    SFVDecl xEquals5, yEquals10, zEquals7;
    SFVExpr onePlusOne, xTimes5, xTimesY, xSquared; // operators
    SFVExpr fxTimes5, fxYtimesX; // functions
    SFVExpr callfxTimes5, callfxYtimesX; // function calls
    SFVExpr ifXfiveElse10, funcOneElse100; // conditionals
    SFVExpr declArr1, declArr2; // decl arrays
    Store<Var, Location> stdEnv = StoreUtils.initializeStd().get(0); // the "standard library"
    Store<Location, Answer> stdStore = StoreUtils.initializeStd().get(1); // the "standard library"

    @Before
    public void init() {
        this.xEquals5 = new SFVDecl(new Var("x"), new Int((long) 5));
        this.yEquals10 = new SFVDecl(new Var("y"), new Int((long) 5));
        this.zEquals7 = new SFVDecl(new Var("z"), new Int((long) 7));

        this.onePlusOne = new Operator(new Int((long) 1), Arrays.asList(new Int((long) 1)), new Var("+"));
        this.xTimes5 = new Operator(new Var("x"), Arrays.asList(new Int((long) 5)), new Var("*"));
        this.xTimesY = new Operator(new Var("x"), Arrays.asList(new Var("y")), new Var("*"));
        this.xSquared = new Operator(new Var("x"), Arrays.asList(new Int((long) 2)), new Var("^"));

        this.declArr1 = new DeclArray(Arrays.asList(this.xEquals5, this.yEquals10, this.zEquals7), this.xTimes5);
        this.declArr2 = new DeclArray(Arrays.asList(this.xEquals5, this.yEquals10, this.zEquals7), this.xTimesY);

        this.fxTimes5 = new Func(Arrays.asList(new Var("x")), this.xTimes5);
        this.fxYtimesX = new Func(Arrays.asList(new Var("x"), new Var("y")), this.xTimesY);

        this.callfxTimes5 = new FuncCall(this.fxTimes5, Arrays.asList((SFVExpr) new Int((long) 10)));
        this.callfxYtimesX = new FuncCall(this.fxYtimesX, Arrays.asList(new Int((long) 10), (SFVExpr) new Int((long) 35)));

        this.ifXfiveElse10 = new Conditional(new Var("x"), new Int((long) 5), new Int((long) 10));


    }

    @Test
    public void parseSVexp() {

    }

    @Test
    public void testErrorMessages() {
        try {
            new Var("x").interpret(this.stdEnv, this.stdStore);
        } catch (Exception e) {
            assertEquals(new IllegalStateException("\"variable x undeclared\"").getMessage(), e.getMessage());
        }
        try {
            this.xTimesY.interpret(this.stdEnv, this.stdStore);
        } catch (Exception e) {
            assertEquals(new IllegalStateException("\"variable y undeclared\"").getMessage(), e.getMessage());
        }
        assertEquals(new Func(Arrays.asList(new Var("x")), new Operator(new Var("x"), new Int( 5l), new Var("*"))), this.fxTimes5.interpret(this.stdEnv, this.stdStore).result);

        try {
            this.xSquared.interpret(this.stdEnv,this.stdStore);
        } catch (Exception e) {
            assertEquals(new IllegalStateException("\"variable x undeclared\"").getMessage(), e.getMessage());
        }
        try {
            new Operator(new Var("y"), Arrays.asList(new Var("x")), new Var("^")).interpret(this.stdEnv,this.stdStore );
        } catch (Exception e) {
            assertEquals(new IllegalStateException("\"variable x undeclared\"").getMessage(), e.getMessage());
        }
        try {
            new DeclArray(Arrays.asList(this.xEquals5, this.yEquals10), new Operator(new Var("x"), Arrays.asList(new Var("y")), new Var("/"))).interpret(this.stdEnv, this.stdStore);
        } catch (Exception e) {
            assertEquals(new IllegalStateException("\"variable / undeclared\"").getMessage(), e.getMessage());
        }

    }

    @Test
    public void testInterpret() {
        assertEquals(new BigInteger("25"), this.declArr1.interpret(this.stdEnv, this.stdStore).result);
        assertEquals(new BigInteger("350"), this.callfxYtimesX.interpret(this.stdEnv, this.stdStore).result);
        assertEquals(new BigInteger("50"), this.callfxTimes5.interpret(this.stdEnv, this.stdStore).result);
        assertEquals(new BigInteger("25"), new DeclArray(Arrays.asList(this.xEquals5), this.xSquared).interpret(this.stdEnv, this.stdStore).result);
        assertEquals(new BigInteger("25"), new DeclArray(Arrays.asList(new SFVDecl(new Var("x"), new Int((long) 20))), this.declArr1).interpret(this.stdEnv, this.stdStore).result);
    }

    @Test
    public void testToJSON() {
        /*SFVExpr hardTest = null;
        try {
            hardTest = ParseUtils.parse(new JSONParser().parse("[[\"let\", \"f\", \"=\", " +
                    "  [\"fun*\", [\"n\"], [\"if-0\", \"n\", 1, [\"call\", \"f\", [\"n\", \"+\", -1]]]]" +
                    " ], " +
                    " [\"call\", \"f\", 2]]"));
        } catch (ParseException e) {
            fail();
            e.printStackTrace();
        }
        System.out.println(hardTest.toJSONString());
        assertEquals(new BigInteger("1"), hardTest.interpret(stdEnv, stdStore).result);*/

        SFVExpr hardTest2 = null;
        try {
            hardTest2 = ParseUtils.parse(new JSONParser().parse("[[\"let\", \"x\", \"=\", 9], [\"let\", \"y\", \"=\", [\"fun*\", [\"a\", \"b\"], [\"if-0\", \"a\", \"b\", [\"a\", \"+\", \"x\"]]]], [[\"let\", \"x\", \"=\", 1], [\"call\", \"y\", 3, 4]]]"));
        } catch (ParseException e) {
            fail();
            e.printStackTrace();
        }
        System.out.println(hardTest2.toJSONString());
        // assertEquals(new BigInteger("11"), hardTest2.interpret(stdEnv, stdStore).result);
    }
}