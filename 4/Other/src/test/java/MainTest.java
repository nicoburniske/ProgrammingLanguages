import answer.Answer;
import answer.AnswerString;
import com.sun.org.apache.xpath.internal.operations.Plus;
import fdecl.FDecl;
import fdecl.FDeclInt;
import fvexpr.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class MainTest {
    FDecl xEquals5, yEquals10, zEquals7;
    FVExpr onePlusOne, xTimes5, yTimesX, xSquared; // operators
    FVExpr fxTimes5, fxYtimesX; // functions
    FVExpr callfxTimes5, callfxYtimesX; // function calls
    FVExpr fiveElse10, funcOneElse100;
    FVExpr declArr1, DeclArr2; // decl arrays
    HashMap<Var, Answer> stdlib = Main.initializeStd(); // the "standard library"


    @Before
    public void init() {
        this.xEquals5 = new FDeclInt(new Var("x"), new Int((long) 5));
        this.yEquals10 = new FDeclInt(new Var("y"), new Int((long) 5));
        this.zEquals7 = new FDeclInt(new Var("z"), new Int((long) 7));

        this.onePlusOne = new Operator(new Int((long) 1), new Int((long) 1), new Var("+"));
        this.xTimes5 = new Operator(new Var("x"), new Int((long) 5), new Var("*"));
        this.yTimesX = new Operator(new Var("x"), new Var("y"), new Var("*"));
        this.xSquared = new Operator(new Var("x"), new Var("2"), new Var("^"));

        this.declArr1 = new DeclArray(Arrays.asList(this.xEquals5, this.yEquals10, this.zEquals7), this.xTimes5);
        this.declArr1 = new DeclArray(Arrays.asList(this.xEquals5, this.yEquals10, this.zEquals7), this.yTimesX);

        this.fxTimes5 = new Func(Arrays.asList(new Var("x")), this.xTimes5);
        this.fxYtimesX = new Func(Arrays.asList(new Var("x"), new Var("y")), this.yTimesX);

        this.callfxTimes5 = new FuncCall(this.fxTimes5, Arrays.asList((FVExpr) new Int((long) 10)));
        this.callfxYtimesX = new FuncCall(this.fxYtimesX, Arrays.asList((FVExpr) new Int((long) 10), (FVExpr) new Int((long) 35)));
    }

    @Test
    public void parseSVexp() {

    }

    @Test
    public void testErrorMessages() {
        assertEquals("variable x undeclared", new Var("x").interpret(this.stdlib).result);
        assertEquals("variable y undeclared", this.yTimesX.interpret(this.stdlib).result);
        assertEquals("closure", this.fxTimes5.interpret(this.stdlib).result);
    }

    @Test
    public void testInterpret() {
        assertEquals(new BigInteger("25"), this.declArr1.interpret(this.stdlib).result);
        assertEquals(new BigInteger("350"), this.callfxYtimesX.interpret(this.stdlib).result);
        assertEquals(new BigInteger("50"), this.callfxTimes5.interpret(this.stdlib).result);

    }

    @Test
    public void testToJSON() {

    }
}