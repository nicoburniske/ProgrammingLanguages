package ast.lhs;

import ast.Var;
import ast.expression.Int;
import ast.expression.VarExpr;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EnvStoreTuple;
import utils.exceptions.ArrayIndexException;
import value.IValueInt;
import value.Location;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ArrIndexLocTest {

    ArrIndexLoc arrIndexLoc1, arrIndexLoc2, arrIndexLoc3, arrIndexLoc4, arrIndexLoc5;
    Location result1;
    EnvStoreTuple containsOne;

    @BeforeEach
    void setUp() {
        arrIndexLoc1 = new ArrIndexLoc(new VarExpr("one"),new Int(1));
        arrIndexLoc2 = new ArrIndexLoc(new VarExpr("one"),new Int(5));
        arrIndexLoc3 = new ArrIndexLoc(new VarExpr("two"),new Int(0));
        arrIndexLoc4 = new ArrIndexLoc(new VarExpr("one"), new VarExpr("one"));
        arrIndexLoc5 = new ArrIndexLoc(new VarExpr("one"), new Int(-1));
        result1 = new Location(2);
        containsOne = new EnvStoreTuple().insert(new Var("one"), Arrays.asList(new IValueInt(5), new IValueInt(6)));
        containsOne = containsOne.insert(new Var("two"), new IValueInt(9));

    }

    @Test
    void lhsInterpreter() {
        assertEquals(result1, arrIndexLoc1.lhsInterpreter(containsOne));
        assertThrows(ArrayIndexException.class, () -> arrIndexLoc2.lhsInterpreter(containsOne));
        assertThrows(ArrayIndexException.class, () -> arrIndexLoc3.lhsInterpreter(containsOne));
        assertThrows(ArrayIndexException.class, () -> arrIndexLoc4.lhsInterpreter(containsOne));
        assertThrows(ArrayIndexException.class, () -> arrIndexLoc5.lhsInterpreter(containsOne));
    }
}