package typechecker.tpal;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import typechecker.parse.Parser;
import typechecker.utils.StandardLib;

import static org.junit.Assert.*;

public class TPALTest {

    @Test
    //Integration like tests
    public void typeCheck() throws ParseException {
        String before = "[\"call\",[\"fun*\", [[\"left\", \":\", \"int\"], [\"right\", \":\", \"int\"],[\"None\", \":\", \"int\"]], \"right\"], 5, 6, 10]";
        String after = "{\"expr\":[\"call\",{\"expr\":[\"fun*\",[[\"left\",\":\",\"int\"],[\"right\",\":\",\"int\"],[\"None\",\":\",\"int\"]],{\"expr\":\"right\",\"type\":\"int\"}],\"type\":[\"int\",\"int\",\"int\",\"->\",\"int\"]},{\"expr\":5,\"type\":\"int\"},{\"expr\":6,\"type\":\"int\"},{\"expr\":10,\"type\":\"int\"}],\"type\":\"int\"}";
        myHelperTest(before, after);

        before = "[\"call\",[\"fun*\", [[\"left\", \":\", \"int\"], [\"right\", \":\", \"int\"],[\"None\", \":\", \"int\"]], [\"left\", \"^\", \"right\"]], 5, 6, 10]";
        after = "{\"expr\":[\"call\",{\"expr\":[\"fun*\",[[\"left\",\":\",\"int\"],[\"right\",\":\",\"int\"],[\"None\",\":\",\"int\"]],{\"expr\":[\"call\",{\"expr\":\"^\",\"type\":[\"int\",\"int\",\"->\",\"int\"]},{\"expr\":\"left\",\"type\":\"int\"},{\"expr\":\"right\",\"type\":\"int\"}],\"type\":\"int\"}],\"type\":[\"int\",\"int\",\"int\",\"->\",\"int\"]},{\"expr\":5,\"type\":\"int\"},{\"expr\":6,\"type\":\"int\"},{\"expr\":10,\"type\":\"int\"}],\"type\":\"int\"}";
        myHelperTest(before, after);

        before = "[[\"let\", [\"b\",\":\",[\"int\", \"->\", \"int\"]], \"=\", [\"fun*\", [[\"a\",\":\",\"int\"]], [\"if-0\", \"a\", [\"call\", \"b\", 1], 2]]], [\"call\", \"b\", 0]]\n" ;
        after = "{\"expr\":[[\"let\",[\"b\",\":\",[\"int\",\"->\",\"int\"]],\"=\",{\"expr\":[\"fun*\",[[\"a\",\":\",\"int\"]],{\"expr\":[\"if-0\",{\"expr\":\"a\",\"type\":\"int\"},{\"expr\":[\"call\",{\"expr\":\"b\",\"type\":[\"int\",\"->\",\"int\"]},{\"expr\":1,\"type\":\"int\"}],\"type\":\"int\"},{\"expr\":2,\"type\":\"int\"}],\"type\":\"int\"}],\"type\":[\"int\",\"->\",\"int\"]}],{\"expr\":[\"call\",{\"expr\":\"b\",\"type\":[\"int\",\"->\",\"int\"]},{\"expr\":0,\"type\":\"int\"}],\"type\":\"int\"}],\"type\":\"int\"}";
        myHelperTest(before, after);
    }

    private void myHelperTest(String before, String after) throws ParseException {
        TPAL tpal = Parser.parseJSON(new JSONParser().parse(before));
        assertEquals(after,tpal.typeCheck(StandardLib.stdLib()).getLeft().toJSONString());
    }
}