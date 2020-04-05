package ast.lhs;

import ast.Var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EnvStoreTuple;
import value.IValueInt;
import value.Location;

import static org.junit.jupiter.api.Assertions.*;

class VarLocTest {

    VarLoc var1;
    Location result1;
    EnvStoreTuple contains1;

    @BeforeEach
    void setUp() {
        var1 = new VarLoc("one");
        result1 = new Location(0);
        contains1 = new EnvStoreTuple().insert(new Var("one"), new IValueInt(98));

    }

    @Test
    void lhsInterpreter() {
        assertEquals(result1, var1.lhsInterpreter(contains1));
    }
}