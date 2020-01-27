package interpreter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class VExprTest {
    VExpr complicatedExample1, complicatedExample2;
    @Before
    public void setUp() throws Exception {
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
    }

    @After
    public void tearDown() throws Exception {
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
}