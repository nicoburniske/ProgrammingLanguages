package fvexpr;

import answer.Answer;
import org.junit.Test;
import store.Store;

import static fvexpr.Constants.ERROR_UNDECLARED_VARIABLE_TEMPLATE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class VarTest {
    @Test
    public void interpretTest() {
        Var x = new Var("x");
        try {
            x.interpret(new Store<Var, Answer>());
            fail();
        } catch (Exception e) {
            assertEquals(String.format(ERROR_UNDECLARED_VARIABLE_TEMPLATE, "x"), e.getMessage());
        }
    }
}
