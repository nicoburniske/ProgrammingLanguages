package cps;

import interpreter.pal.*;
import interpreter.parser.Parser;
import interpreter.value.ValueInt;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ToySeqTest {

    String toBeParsed;
    Toy parseResult;

    @Before
    public void setUp() {
        toBeParsed = "[\"seq*\",1,2,3,4]";
        parseResult = new ToyCall(new ToyFunc(
                Arrays.asList(
                        new ToyVar("four"),
                        new ToyVar("three"),
                        new ToyVar("two"),
                        new ToyVar("one")), new ToyVar("four")),
                Arrays.asList(new ToyInt(4L),
                        new ToyInt(3L),
                        new ToyInt(2L),
                        new ToyInt(1L)));
    }

    @Test
    public void parseToDeclArray() throws ParseException {
        Object obj = new JSONParser().parse(toBeParsed);
        Parser.generateValidSeqNames(obj);
        Toy parsed = Parser.parse(obj);
        assertTrue(Toy.alphaEquals(parseResult, parsed));
        assertEquals(new ValueInt(4L), parsed.run());

    }
}
