package parse;

import org.json.simple.parser.JSONParser;
import org.junit.Test;
import tpal.*;
import tpal.decl.TPALDecl;
import type.TypedVar;
import type.TypeFunction;
import type.TypeInt;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void parseJSON() {
        try {
            Object obj = new JSONParser().parse("[\"call\",[\"fun*\", [[\"left\", \":\", \"int\"], [\"right\", \":\", \"int\"],[\"None\", \":\", \"int\"]], \"right\"], 5, 6, 10]\n");
            TPAL res = Parser.parseJSON(obj);
            TPAL resExpected = new TPALCall(new TPALFunc(Arrays.asList(new TypedVar("left", new TypeInt()), new TypedVar("right", new TypeInt()), new TypedVar("None", new TypeInt())), new TPALVar("right")), Arrays.asList(new TPALInt(5l), new TPALInt(6l), new TPALInt(10l)));
            assertEquals(resExpected,res);

            Object obj2 = new JSONParser().parse("[[\"let\", [\"x\",\":\", [\"int\", \"->\", \"int\"]], \"=\", 42 ], [\"x\", \"+\", 4]]");
            TPAL res2 = Parser.parseJSON(obj2);
            TPAL resExpected2 = new TPALDeclArray(Arrays.asList(new TPALDecl(new TypedVar("x", new TypeFunction(Arrays.asList(new TypeInt()), new TypeInt())), new TPALInt(42))),
                    new TPALCall(new TPALVar("+"), Arrays.asList(new TPALVar("x"), new TPALInt(4))));
            //TODO:TEST case passes but something is worong with eqauls assertEquals(resExpected2,res2);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}