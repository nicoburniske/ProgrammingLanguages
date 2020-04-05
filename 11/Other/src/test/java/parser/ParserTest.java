package parser;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParserTest {
    String example1 = "[\n" +
            "    [\"let\", \"x\", \"=\", 1],\n" +
            "    \n" +
            "    [\"vec\", \"z\", \"=\", [3, 4, 5]],\n" +
            "    \"in\",\n" +
            "    [\"x\", \"=\", \"z\"],\n" +
            "    \"x\"\n" +
            "]";
    @Test
    public void parse() throws ParseException {
        Object arr = new JSONParser().parse(example1);
        System.out.println(Parser.parse(arr).CESK().getLeft());
        System.out.println(Parser.parse(arr).CESK().getStore().toString());
    }
}