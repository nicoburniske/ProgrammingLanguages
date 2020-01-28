package interpreter;

import jfkbits.Atom;
import jfkbits.ExprList;
import jfkbits.LispParser;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static parser.ParseUtils.parseSVexp;

public class VExprTest {
    VExpr complicatedExample1, complicatedExample2;
    LispParser.Expr exprOnePlusX, expr10TimesX, exprXEquals10TimesX, exprXEquals9, exprOnePlusOne, expr1, expr2, expr3;
    VExpr expr1Solution, expr2Solution, expr3Solution;

    @Before
    public void setUp() {
        complicatedExample1 = new VDeclArray(Arrays.asList(
                new Decl(new Var("x"), new VInt(1)),
                new Decl(new Var("y"), new VInt(2)),
                new Decl(new Var("m"), new VInt(100))),
                new VOperator(
                        new VDeclArray(Arrays.asList(
                                new Decl(new Var("m"), new VInt(4))),
                                new VOperator(new VarPair(0, 0), new VarPair(1, 1), "*")),
                        new VarPair(0, 2), "*"));

        complicatedExample2 = new VDeclArray(Arrays.asList(new Decl(new Var("x"), new VInt(5)),
                new Decl(new Var("x"), new VOperator(new VarPair(0, 0), new VInt(6), "+")),
                new Decl(new Var("y"), new VOperator(new VarPair(0, 1), new VInt(5), "*"))),
                new VOperator(new VarPair(0, 1), new VarPair(0, 2), "+"));

        exprOnePlusX = new ExprList();
        ((ExprList) exprOnePlusX).add(new Atom("1"));
        ((ExprList) exprOnePlusX).add(new Atom("+"));
        ((ExprList) exprOnePlusX).add(new Atom("x"));

        exprXEquals9 = new ExprList();
        ((ExprList) exprXEquals9).add(new Atom("let"));
        ((ExprList) exprXEquals9).add(new Atom("x"));
        ((ExprList) exprXEquals9).add(new Atom("="));
        ((ExprList) exprXEquals9).add(new Atom("9"));

        expr1 = new ExprList();
        ((ExprList) expr1).add(exprXEquals9);
        ((ExprList) expr1).add(exprOnePlusX);

        expr1Solution = new VDeclArray(Arrays.asList(new Decl(new Var("x"), new VInt(9))),
                new VOperator(new VInt(1), new Var("x"), "+"));

        expr10TimesX = new ExprList();
        ((ExprList) expr10TimesX).add(new Atom("10"));
        ((ExprList) expr10TimesX).add(new Atom("*"));
        ((ExprList) expr10TimesX).add(new Atom("x"));

        exprXEquals10TimesX = new ExprList();
        ((ExprList) exprXEquals10TimesX).add(new Atom("let"));
        ((ExprList) exprXEquals10TimesX).add(new Atom("x"));
        ((ExprList) exprXEquals10TimesX).add(new Atom("="));
        ((ExprList) exprXEquals10TimesX).add(expr10TimesX);

        expr2 = new ExprList();
        ((ExprList) expr2).add(exprXEquals9);
        ((ExprList) expr2).add(exprXEquals10TimesX);
        ((ExprList) expr2).add(new Atom("x"));

        expr2Solution = new VDeclArray(Arrays.asList(new Decl(new Var("x"), new VInt(9)), new Decl(new Var("x"), new VOperator(new VInt(10), new Var("x"), "*"))), new Var("x"));

        expr3 = new ExprList();
        ((ExprList) expr3).add(exprXEquals10TimesX);
        ((ExprList) expr3).add(exprXEquals9);
        ((ExprList) expr3).add(exprOnePlusX);

        expr3Solution = new VDeclArray(Arrays.asList(new Decl(new Var("x"), new VOperator(new VInt(10), new Var("x"), "*")), new Decl(new Var("x"), new VInt(9))), new VOperator(new VInt(1), new Var("x"), "+"));
        exprOnePlusOne = new ExprList();
        ((ExprList) exprOnePlusOne).add(new Atom("1"));
        ((ExprList) exprOnePlusOne).add(new Atom("+"));
        ((ExprList) exprOnePlusOne).add(new Atom("1"));

    }

    @Test
    public void evaluate() {
        assertEquals(2, complicatedExample1.getMaxNumberOfScopedVariables());
        assertEquals(800, complicatedExample1.evaluate(new StackList<StackList<Integer>>(2)));
        assertEquals(1, complicatedExample2.getMaxNumberOfScopedVariables());
        assertEquals(66, complicatedExample2.evaluate(new StackList<StackList<Integer>>(1)));
    }

    @Test
    public void getMaxNumberOfScopedVariables() {
        assertEquals(2, complicatedExample1.getMaxNumberOfScopedVariables());
        assertEquals(1, complicatedExample2.getMaxNumberOfScopedVariables());
    }

    @Test
    public void testAST() {
        assertTrue(parseSVexp(new Atom("x")).equals(new Var("x")));
        assertTrue(parseSVexp(new Atom("1")).equals(new VInt(1)));
        assertTrue(parseSVexp(exprOnePlusOne).equals(new VOperator(new VInt(1), new VInt(1), "+")));
        assertTrue(parseSVexp(expr1).equals(expr1Solution));
        assertTrue(parseSVexp(expr2).equals(expr2Solution));
        assertTrue(parseSVexp(expr3).equals(expr3Solution));
    }
}