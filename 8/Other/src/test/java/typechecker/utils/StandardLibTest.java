package typechecker.utils;

import org.junit.Test;
import typechecker.env.Tuple;
import typechecker.tpal.TPALCall;
import typechecker.tpal.TPALInt;
import typechecker.tpal.TPALVar;

import java.util.Arrays;

import static org.junit.Assert.*;
import static typechecker.utils.Constants.ERROR_ARGS_PARAMS_COUNT_DONT_MATCH;

public class StandardLibTest {

    @Test
    public void stdLib() {
        checkAritityFail("+", 0, false);
        checkAritityFail("+", 1, false);
        checkAritityFail("+", 2, true);
        checkAritityFail("+", 3, false);
        checkAritityFail("*", 0, false);
        checkAritityFail("*", 1, false);
        checkAritityFail("*", 2, true);
        checkAritityFail("*", 3, false);
        checkAritityFail("^", 0, false);
        checkAritityFail("^", 1, false);
        checkAritityFail("^", 2, true);
        checkAritityFail("^", 3, false);
        checkAritityFail("@", 0, false);
        checkAritityFail("@", 1, true);
        checkAritityFail("@", 2, false);
        checkAritityFail("@", 3, false);
        checkAritityFail("!", 0, false);
//        checkAritityFail("!", 1, true);
        checkAritityFail("!", 2, false);
        checkAritityFail("!", 3, false);
        checkAritityFail("=", 0, false);
        checkAritityFail("=", 1, false);
//        checkAritityFail("=", 2, true); // these need to be done seperatly as they need differnt args passed in to work
        checkAritityFail("=", 3, false);

        //Will throw error if ever fails
        Tuple x = (new TPALCall(new TPALVar("!"), Arrays.asList(new TPALCall(new TPALVar("@"), Arrays.asList(new TPALInt(1L)))))).typeCheck(StandardLib.stdLib());
        Tuple y = (new TPALCall(new TPALVar("="), Arrays.asList(new TPALCall(new TPALVar("@"), Arrays.asList(new TPALInt(1L))), new TPALInt(23)))).typeCheck(StandardLib.stdLib());



    }

    private void checkAritityFail(String func, int num, boolean pass) {
        try{
            if (num == 0) {
                Tuple x = (new TPALCall(new TPALVar(func), Arrays.asList())).typeCheck(StandardLib.stdLib());
            } else if(num == 1) {
                Tuple x = (new TPALCall(new TPALVar(func), Arrays.asList(new TPALInt(1L)))).typeCheck(StandardLib.stdLib());
            } else if (num == 2) {
                Tuple x = (new TPALCall(new TPALVar(func), Arrays.asList(new TPALInt(1L), new TPALInt(1L)))).typeCheck(StandardLib.stdLib());
            } else if (num == 3) {
                Tuple x = (new TPALCall(new TPALVar(func), Arrays.asList(new TPALInt(1L), new TPALInt(1L), new TPALInt(1L)))).typeCheck(StandardLib.stdLib());
            }
            if(!pass) {
                fail();
            }
        } catch (IllegalStateException e) {
            if(!pass) {
                assertEquals(ERROR_ARGS_PARAMS_COUNT_DONT_MATCH, e.getMessage());
            } else {
                fail();
            }
        }
    }
}